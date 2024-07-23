import io.swagger.v3.parser.OpenAPIV3Parser
import io.swagger.v3.parser.core.models.ParseOptions
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ReferenceTest {
    private val specV1 = "src/main/kotlin/v1/api_schema.yaml"
    private val specV2 = "src/main/kotlin/v2/api_schema.yaml"
    private val parseOpts = ParseOptions().also { it.isResolve = true }
    private val v1Result = OpenAPIV3Parser().read(specV1, null, parseOpts)
    private val v2Result = OpenAPIV3Parser().read(specV2, null, parseOpts)
    private val v1Example = v1Result.paths["/products"]?.get?.responses?.get("200")?.content?.get("application/json")!!
    private val v2Example = v2Result.paths["/products"]?.get?.responses?.get("200")?.content?.get("application/json")!!

    @Test
    fun `parsed example values should be equal`() {
        assertEquals(v2Example.examples["GET_PRODUCT"]?.value, v1Example.examples["GET_PRODUCT"]?.value)
    }

    @Test
    fun `parsed example value types should be same`() {
        assertEquals(v2Example.examples["GET_PRODUCT"]?.value?.javaClass, v1Example.examples["GET_PRODUCT"]?.value?.javaClass)
    }
}