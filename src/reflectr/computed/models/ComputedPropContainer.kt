package reflectr.computed.models

import reflectr.models.Mapped

/**
 * Represents a container holding a computed property, which may or may not be resolved.
 *
 * @property obj        the instance of [TMapped] this computed property is attached to
 * @property resolution the resolution state of the computed property
 *
 * @author Benjozork
 */
abstract class ComputedPropContainer<TMapped : Mapped, TProperty : Any?> {

    abstract val obj: TMapped

    var resolution: Resolution = Resolution.Unresolved

    abstract class AutomaticallyResolvable<TMapped : Mapped, TProperty : Any?> : ComputedPropContainer<TMapped, TProperty>() {
        abstract fun resolve(): TProperty
    }

    /**
     * Represents the resolution state of the computed property
     */
    sealed class Resolution {
        /**
         * Used when a computed property's value is known and resolved
         */
        class Value<TProperty : Any?>(val value: TProperty) : Resolution()

        /**
         * Used when a computed property's value is unknown, but there was a resolution attempt
         */
        object Undefined : Resolution()

        /**
         * Used when a computed property's value is unknown, and there was no resolution attempt
         */
        object Unresolved : Resolution()

        override fun equals(other: Any?): Boolean {
            return if (this is Value<*> && other is Value<*>)
                this.value == other.value
            else false
        }

        override fun hashCode(): Int {
            return javaClass.hashCode()
        }
    }

}
