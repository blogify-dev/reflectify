package reflectr.computed.models

import reflectr.models.Mapped

/**
 * A simple [computed property container][ComputedPropContainer] that simply emits a value using a function
 *
 * @author Benjozork
 */
class BasicComputedProperty<TMapped : Mapped, TProperty : Any?> (
    override val obj: TMapped,
    val function: () -> TProperty
) : ComputedPropContainer.AutomaticallyResolvable<TMapped, TProperty>() {

    override fun resolve() = function()

}
