package reflectr.analysis.metadata

import reflectr.analysis.extensions.descriptor
import reflectr.analysis.models.PropertyDescriptor
import reflectr.models.Mapped

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertDoesNotThrow
import kotlin.reflect.KProperty1

class ClassDescriptorTest {

    private class A (
        val name: String,
        val age: Int
    ) : Mapped()

    @Test fun `should find class descriptor for simple class`() {
        assertDoesNotThrow {
            A::class.descriptor
        }
    }

    @Test fun `should have all property descriptors in simple class descriptor`() {
        val descriptor = A::class.descriptor

        arrayOf(A::name, A::age).forEach { property ->
            assertTrue(descriptor.propertyDescriptors.contains<KProperty1<*, *>, PropertyDescriptor>(property))
        }
    }

}
