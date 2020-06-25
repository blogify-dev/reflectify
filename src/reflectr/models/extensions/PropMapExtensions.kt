package reflectr.models.extensions

import reflectr.util.filterThenMapValues
import reflectr.models.PropMap

/**
 * Only returns [PropMap.PropertyHandle]s that respect interface [PropMap.PropertyHandle.Valid]
 *
 * @author Benjozork
 */
val PropMap.valid
    get() = this.map.filterThenMapValues (
        { it is PropMap.PropertyHandle.Valid },
        { it.value as PropMap.PropertyHandle.Valid }
    )

/**
 * Only returns [PropMap.PropertyHandle]s that are of type [PropMap.PropertyHandle.Ok]
 *
 * @author Benjozork
 */
val PropMap.ok
    get() = this.map.filterThenMapValues (
        { it is PropMap.PropertyHandle.Ok },
        { it.value as PropMap.PropertyHandle.Ok }
    )

/**
 * Only returns [PropMap.PropertyHandle]s that are of type [PropMap.PropertyHandle.Computed]
 *
 * @author Benjozorkse
 *
 */
val PropMap.computed
    get() = this.map.filterThenMapValues (
        { it is PropMap.PropertyHandle.Computed },
        { it.value as PropMap.PropertyHandle.Computed }
    )

/**
 * Only returns [PropMap.PropertyHandle]s that are of type [PropMap.PropertyHandle.AccessDenied]
 *
 * @author Benjozork
 */
val PropMap.accessDenied
    get() = this.map.filterThenMapValues (
        { it is PropMap.PropertyHandle.AccessDenied },
        { it.value as PropMap.PropertyHandle.AccessDenied }
    )
