package api.utilities;

import static org.hamcrest.Matchers.*;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

import java.io.File;
import java.util.List;

public class CommonApiValidations {
	
	 // ============================
    // 1. Status & Protocol
    // ============================
    public static void validateStatusCode(Response response, int expectedStatusCode) {
        response.then().statusCode(expectedStatusCode);
    }

    public static void validateStatusCodeInRange(Response response, int min, int max) {
        response.then().statusCode(allOf(greaterThanOrEqualTo(min), lessThanOrEqualTo(max)));
    }

    public static void validateStatusLine(Response response, String expectedStatusLine) {
        response.then().statusLine(expectedStatusLine);
    }

    // ============================
    // 2. Response Time
    // ============================
    public static void validateResponseTime(Response response, long maxTimeInMs) {
        response.then().time(lessThan(maxTimeInMs));
    }

    // ============================
    // 3. Headers
    // ============================
    public static void validateHeader(Response response, String headerName, String expectedValue) {
        response.then().header(headerName, equalTo(expectedValue));
    }

    public static void validateHeaderContains(Response response, String headerName, String partialValue) {
        response.then().header(headerName, containsString(partialValue));
    }

    public static void validateHeaderExists(Response response, String headerName) {
        response.then().header(headerName, notNullValue());
    }

    // ============================
    // 4. Content Type
    // ============================
    public static void validateContentType(Response response, ContentType expectedContentType) {
        response.then().contentType(expectedContentType);
    }

    // ============================
    // 5. JSON Body Validations
    // ============================
    public static void validateJsonField(Response response, String jsonPath, Object expectedValue) {
        response.then().body(jsonPath, equalTo(expectedValue));
    }

    public static void validateJsonFieldNotNull(Response response, String jsonPath) {
        response.then().body(jsonPath, notNullValue());
    }

    public static void validateJsonFieldIsEmpty(Response response, String jsonPath) {
        response.then().body(jsonPath, empty());
    }

    public static void validateJsonFieldMatchesRegex(Response response, String jsonPath, String regex) {
        response.then().body(jsonPath, matchesRegex(regex));
    }

    public static void validateJsonFieldIsNumber(Response response, String jsonPath) {
        response.then().body(jsonPath, instanceOf(Number.class));
    }

    public static void validateJsonFieldIsString(Response response, String jsonPath) {
        response.then().body(jsonPath, instanceOf(String.class));
    }

    public static void validateJsonFieldIsBoolean(Response response, String jsonPath) {
        response.then().body(jsonPath, instanceOf(Boolean.class));
    }

    public static void validateFieldGreaterThan(Response response, String jsonPath, int minValue) {
        response.then().body(jsonPath, greaterThan(minValue));
    }

    public static void validateFieldLessThan(Response response, String jsonPath, int maxValue) {
        response.then().body(jsonPath, lessThan(maxValue));
    }

    // ============================
    // 6. JSON Array Validations
    // ============================
    public static void validateJsonArraySize(Response response, String jsonPath, int expectedSize) {
        response.then().body(jsonPath + ".size()", equalTo(expectedSize));
    }

    public static void validateJsonArrayMinSize(Response response, String jsonPath, int minSize) {
        response.then().body(jsonPath + ".size()", greaterThanOrEqualTo(minSize));
    }

    public static void validateJsonArrayMaxSize(Response response, String jsonPath, int maxSize) {
        response.then().body(jsonPath + ".size()", lessThanOrEqualTo(maxSize));
    }

    public static void validateJsonArrayContains(Response response, String jsonPath, Object expectedValue) {
        response.then().body(jsonPath, hasItem(expectedValue));
    }

    public static void validateJsonArrayContainsAll(Response response, String jsonPath, Object... expectedValues) {
        response.then().body(jsonPath, hasItems(expectedValues));
    }

    public static void validateJsonArrayUnique(Response response, String jsonPath, List<?> values) {
        long distinctCount = values.stream().distinct().count();
        if (distinctCount != values.size()) {
            throw new AssertionError("Array at path " + jsonPath + " contains duplicate values");
        }
    }

    // ============================
    // 7. Schema Validation
    // ============================
    public static void validateJsonSchema(Response response, String schemaFilePath) {
        File schema = new File(schemaFilePath);
        response.then().body(JsonSchemaValidator.matchesJsonSchema(schema));
      //  System.out.println("JSON schema validation: "+response.then().body(JsonSchemaValidator.matchesJsonSchema(schema)));
    }

    // ============================
    // 8. Error Handling
    // ============================
    public static void validateErrorMessage(Response response, String jsonPath, String expectedMessage) {
        response.then().body(jsonPath, equalTo(expectedMessage));
    }

    public static void validateUnauthorized(Response response) {
        response.then().statusCode(401);
    }

    public static void validateForbidden(Response response) {
        response.then().statusCode(403);
    }

    public static void validateNotFound(Response response) {
        response.then().statusCode(404);
    }

    public static void validateServerError(Response response) {
        response.then().statusCode(500);
    }

    // ============================
    // 9. Response Body Text
    // ============================
    public static void validateResponseContainsText(Response response, String expectedText) {
        response.then().body(containsString(expectedText));
    }

    public static void validateResponseNotContainsText(Response response, String unexpectedText) {
        response.then().body(not(containsString(unexpectedText)));
    }

    // ============================
    // 10. Business Rule Checks
    // ============================
    public static void validateFieldLength(Response response, String jsonPath, int min, int max) {
        String value = response.jsonPath().getString(jsonPath);
        if (value.length() < min || value.length() > max) {
            throw new AssertionError("Field length out of range for path: " + jsonPath);
        }
    }

    public static void validateDateFormat(Response response, String jsonPath, String regexForDateFormat) {
        response.then().body(jsonPath, matchesRegex(regexForDateFormat));
    }

	
}
