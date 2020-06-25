package reflectify.entity

import reflectify.util.Sr
import reflectify.unsafePropMap
import reflectify.entity.instantiation.construct
import reflectify.models.Mapped
import reflectify.models.PropMap
import reflectify.models.extensions.ok
import reflectify.slice

import com.fasterxml.jackson.databind.ObjectMapper
import reflectify.util.MappedData

import java.util.UUID

import kotlin.reflect.KClass

/**
 * Updates a [Entity] using a map of [`Ok` handles][PropMap.PropertyHandle.Ok] to new data values
 *
 * @receiver the [Entity] to update
 *
 * @param R       the class associated with [this]
 * @param rawData a map of [`Ok` handles][PropMap.PropertyHandle.Ok] to new data values
 *
 * @return an updated instance of [R] with all new data from [rawData], but the same unchanged data from [this]
 *
 * @author Benjozork
 */
@ExperimentalStdlibApi
suspend fun <R : Mapped> R.update (
    rawData: MappedData,
    objectMapper: ObjectMapper,
    fetcher: suspend (KClass<Entity>, UUID) -> Sr<Entity>
): Sr<R> {
    val targetPropMap = this.unsafePropMap // Get unsafe handles too

    // Find parameters we are not changing
    val notUpdatedParameters = (targetPropMap.ok.values subtract rawData.keys)

    // Find parameters we are changing
    val updatedParameters = (targetPropMap.ok.values intersect rawData.keys)

    // Find the values of the unchanged params
    val unchangedValues = this
        .slice(notUpdatedParameters.map { it.name }.toSet(), unsafe = true)
        .filter { !it.key.startsWith('_') }
        .mapKeys { targetPropMap.ok[it.key] ?: error("fatal: unknown propHandle slipped in !") }

    // Find the values of the changed params
    val changedValues = updatedParameters
        .associateWith { rawData[it] }

    return this::class.construct(unchangedValues + changedValues, objectMapper, fetcher)
}
