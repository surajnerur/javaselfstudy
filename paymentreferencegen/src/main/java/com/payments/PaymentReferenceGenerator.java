package com.payments;
import java.util.ArrayList;
import java.util.List;

public class PaymentReferenceGenerator {

	public static final String STARTING_DIGITS="10";
	public static final String MULTIPLIERS="731";
	
	public String getPaymentReferenceNumber(String creditAccountNumber) {
		String paymentRefString = STARTING_DIGITS+creditAccountNumber;
		StringBuilder paymentRef = new StringBuilder(paymentRefString);
		//List<Integer> numberList = new ArrayList<Integer>();
		StringBuilder reversePaymentRef=new StringBuilder(paymentRefString).reverse();
		StringBuilder multiplierBuilder = generateMultiplier(reversePaymentRef.length());
		int sum=0;
		for(int i =0; i<reversePaymentRef.length(); i++) {
			int number = Integer.parseInt(String.valueOf(reversePaymentRef.charAt(i)));
			int multiplier = Integer.parseInt(String.valueOf(multiplierBuilder.charAt(i)));
			int result = Math.multiplyExact(number, multiplier); 
			sum = sum + result;
			System.out.println(multiplier + " * " + number + " = " + result);
			//numberList.add(Integer.parseInt(String.valueOf(reversePaymentRef.charAt(i))));
		}
		System.out.println("Total Sum = "+sum);
		 long roundedOffNum =Math.round(Math.ceil(sum/10.0) * 10);
		 int controlNum = Math.subtractExact(Math.toIntExact(roundedOffNum), sum);
		 System.out.println("Control Number = " + roundedOffNum + " - " + sum + " = " + controlNum);
		 paymentRef.append(controlNum);
		 System.out.println("Payment Ref Number : " + paymentRef);
		return paymentRef.toString();
	}
	
	private StringBuilder generateMultiplier(int paymentRefLen) {
		StringBuilder multiplierBuilder = new StringBuilder();
		int j=0;
		for(int i=0; i<paymentRefLen; i++) {
			multiplierBuilder.append(MULTIPLIERS.substring(j,j+1));
			if(j<MULTIPLIERS.length()-1) {
				j++;
			}else{
				j=0;
			}
		}
		return multiplierBuilder;
	}
	
	/*public static void main(String[] args) {
		String s = "731";
		System.out.println(s.substring(1, 2));
       PaymentReferenceGenerator gen= new PaymentReferenceGenerator();
  //     gen.generateMultiplier(9);
       System.out.println(gen.getPaymentReferenceNumber("1234567"));
       System.out.println(gen.getPaymentReferenceNumber("1234568"));
       System.out.println(Math.round(Math.ceil(19/10.0) * 10));
       System.out.println(Math.subtractExact(Math.toIntExact(Math.round(Math.ceil(19/10.0) * 10)), 19));
	}*/

}
