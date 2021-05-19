import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CalculatorServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String myResp = request.getParameter("calculatingString");
        String[] calculatingArray = myResp.split(" ");
        int numberA = Integer.parseInt(calculatingArray[0]);
        int numberB = Integer.parseInt(calculatingArray[2]);
        String operation = calculatingArray[1];
        int result = 0;

        switch(operation){
            case "+":
                result = numberA + numberB;
                break;
            case "-":
                result = numberA - numberB;
                break;
            case "*":
                result = numberA * numberB;
                break;
            case "/":
                result = numberA / numberB;
                break;
        }

        PrintWriter printWriter = response.getWriter();
        printWriter.println(result);
    }

}