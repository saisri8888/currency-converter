import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.HashMap;

public class CurrencyConverter extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        double amount = Double.parseDouble(req.getParameter("amount"));
        String from = req.getParameter("from");
        String to = req.getParameter("to");

        double result = convert(amount, from, to);

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.println("<html><head><title>Result</title></head><body style='text-align:center;font-family:Arial'>");
        out.println("<h1>Currency Conversion Result</h1>");
        out.println("<h2>" + amount + " " + from + " = " + result + " " + to + "</h2>");
        out.println("<a href='index.html'>Convert Again</a>");
        out.println("</body></html>");
    }

    private double convert(double amount, String from, String to) {
        HashMap<String, Double> rates = new HashMap<>();
        rates.put("USD", 1.0);
        rates.put("INR", 83.0);
        rates.put("EUR", 0.93);
        rates.put("GBP", 0.79);
        rates.put("JPY", 157.0);
        rates.put("AUD", 1.52);
        rates.put("CAD", 1.37);

        double inUSD = amount / rates.get(from);  // Convert to USD
        return Math.round(inUSD * rates.get(to) * 100.0) / 100.0;
    }
}
