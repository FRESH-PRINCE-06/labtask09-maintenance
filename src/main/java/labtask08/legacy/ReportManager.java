package labtask08.legacy;

import java.util.List;

/**
 * THE LEGACY CODE (kept unchanged for comparison). It contains four smells:
 *  1. String concatenation inside a loop
 *  2. NullPointerException risk on type.equals(...)
 *  3. Hard-coded PDF/CSV if-else chain (violates Open/Closed)
 *  4. Static, untestable EmailClient dependency
 */
public class ReportManager {

    public void generate(String type, List<String> data, boolean sendEmail) {
        String output = "";
        for (int i = 0; i < data.size(); i++) {
            output += data.get(i) + ",";            // Smell 1
        }
        if (type.equals("PDF")) {                   // Smell 2 & 3
            System.out.println("Writing PDF: " + output);
        } else if (type.equals("CSV")) {
            System.out.println("Writing CSV: " + output);
        }
        if (sendEmail) {                            // Smell 4
            EmailClient.sendStatic("admin@company.com", output);
        }
    }
}
