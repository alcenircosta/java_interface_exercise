package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Contract;
import model.entities.Installment;
import model.services.ContractService;
import model.services.PaypalService;

public class Program {
	public static void main(String[] args) throws ParseException {
		Scanner sc = new Scanner(System.in);
		Locale.setDefault(Locale.US);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		System.out.println("Enter contract data:");
		System.out.print("Number: ");
		Integer number = sc.nextInt();
		System.out.print("Date (dd/mm/yyyy):");
		Date date = sdf.parse(sc.next());
		System.out.print("Contract value:");
		double contractValue = sc.nextDouble();

		Contract contract = new Contract(number, date, contractValue);

		System.out.print("Enter number of installments: ");
		int months = sc.nextInt();

		ContractService cs = new ContractService(new PaypalService());
		cs.processContract(contract, months);
		
		System.out.println("Installments: ");
		for (Installment it: contract.getInstallments()) {
			System.out.println(it);
		}
		sc.close();
	}
}
