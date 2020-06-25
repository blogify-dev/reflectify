package reflectify.analysis

import reflectify.analysis.models.ClassDescriptor
import reflectify.models.Mapped

import kotlin.reflect.KClass

import java.util.concurrent.ConcurrentHashMap

internal object ClassAnalysisCache {

    private val classAnalysisCache = ConcurrentHashMap<KClass<out Mapped>, ClassDescriptor>()

    operator fun get(klass: KClass<out Mapped>): ClassDescriptor =
        classAnalysisCache.getOrPut(klass) { ClassDescriptor(klass) }

}
