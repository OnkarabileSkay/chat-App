
package login;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import java.lang.reflect.Method;

public class TestRunner {
    
    public static void main(String[] args) {
        System.out.println("\n");
        System.out.println("      TEST RESULTS");
        System.out.println("\n");
        
        // Run all tests in PoePart3Test class
        Result result = JUnitCore.runClasses(PoePart3Test.class);
        
        // Get all test methods from the test class
        Method[] methods = PoePart3Test.class.getDeclaredMethods();
        
        // Track passed and failed tests
        int passed = 0;
        int failed = 0;
        
        System.out.println("Individual Test Results:");
        System.out.println("");
        
        // Check each test method
        for (Method method : methods) {
            if (method.isAnnotationPresent(org.junit.Test.class)) {
                String testName = method.getName();
                boolean testPassed = true;
                String errorMessage = "";
                
                // Check if this test failed
                for (Failure failure : result.getFailures()) {
                    if (failure.getDescription().getMethodName().equals(testName)) {
                        testPassed = false;
                        errorMessage = failure.getMessage();
                        break;
                    }
                }
                
                if (testPassed) {
                    System.out.println(" " + testName + ": PASSED");
                    passed++;
                } else {
                    System.out.println(" " + testName + ": FAILED");
                    System.out.println("  Error: " + errorMessage);
                    failed++;
                }
            }
        }
        
        System.out.println("\n");
        System.out.println("Details:");
        System.out.println("  Total Tests: " + (passed + failed));
        System.out.println("  Passed: " + passed + " .");
        System.out.println("  Failed: " + failed + " .");
        System.out.println("  Time: " + result.getRunTime() + "ms");
        System.out.println("\n");
        // Exit with error code if any test failed
        System.exit(result.wasSuccessful() ? 0 : 1);
    }
}