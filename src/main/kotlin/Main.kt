package com.example

import io.swagger.v3.parser.OpenAPIV3Parser
import io.swagger.v3.parser.core.models.ParseOptions

fun main() {
    val specV1 = "src/main/kotlin/v1/api_schema.yaml"
    val specV2 = "src/main/kotlin/v2/api_schema.yaml"
    val parseOpts = ParseOptions().also { it.isResolve = true }
    val v1Result = OpenAPIV3Parser().read(specV1, null, parseOpts)
    val v2Result = OpenAPIV3Parser().read(specV2, null, parseOpts)
    val v1Example = v1Result.paths["/products"]?.get?.responses?.get("200")?.content?.get("application/json")!!
    val v2Example = v2Result.paths["/products"]?.get?.responses?.get("200")?.content?.get("application/json")!!

    println()
    println("<-------- EXAMPLE VALUE ----------->")
    println("V1 Example Value: ${v1Example.examples["GET_PRODUCT"]?.value}")
    println("V2 Example Value: ${v2Example.examples["GET_PRODUCT"]?.value}")
    println()

    println("<-------- EXAMPLE VALUE TYPE ----------->")
    println("V1 Example Value Type: ${v1Example.examples["GET_PRODUCT"]?.value?.javaClass}")
    println("V2 Example Value Type: ${v2Example.examples["GET_PRODUCT"]?.value?.javaClass}")
}