package hu.neuron.app;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MethodRuntimeAnalyzer {


		private static final Logger logger = Logger.getLogger(MethodRuntimeAnalyzer.class.getName());

		Class clazz1 = null;
		List<Integer> list = null;
		private BufferedWriter bufferedWriter;

		public MethodRuntimeAnalyzer(BufferedWriter bufferedWriter) {
			this.bufferedWriter = bufferedWriter;
		}

		private void runAdd(Integer times) throws IOException {

			Long startTime = System.nanoTime();
			for (int i = 0; i < times; i++) {
				list.add(i);

			}
			Long endTime = System.nanoTime();
			Long ellapsedTime = endTime - startTime;
			bufferedWriter.write("<td>"+ellapsedTime.toString()+" ns</td>");

		}

		private void runInsertAnElement(Integer times) throws IOException {

			Long startTime = System.nanoTime();
			for (int i = 0; i < times; i++) {
				list.add(i, i);

			}
			Long endTime = System.nanoTime();
			Long ellapsedTime = endTime - startTime;

			bufferedWriter.write("<td>"+ellapsedTime.toString()+" ns</td>");

		}

		private void runAddAll(List list2) throws IOException {

			Long startTime = System.nanoTime();
			list.addAll(list2);
			Long endTime = System.nanoTime();
			Long ellapsedTime = endTime - startTime;

			bufferedWriter.write("<td>"+ellapsedTime.toString()+" ns</td>");


		}

		private void runAddAllToSpecificIndex(List list2, Integer index) throws IOException {

			Long startTime = System.nanoTime();
			list.addAll(index, list2);
			Long endTime = System.nanoTime();
			Long ellapsedTime = endTime - startTime;
			
			bufferedWriter.write("<td>"+ellapsedTime.toString()+" ns</td>");


		}

		private void runClear() throws IOException {

			Long startTime = System.nanoTime();
			list.clear();
			Long endTime = System.nanoTime();
			Long ellapsedTime = endTime - startTime;

			bufferedWriter.write("<td>"+ellapsedTime.toString()+" ns</td></tr>");

		}

		private void runContainsAnElement(List list2) throws IOException {

			Long startTime = System.nanoTime();

			for (int i = 0; i < list2.size(); i++) {
				list.contains(i);
			}
			Long endTime = System.nanoTime();
			Long ellapsedTime = endTime - startTime;

			bufferedWriter.write("<td>"+ellapsedTime.toString()+" ns</td>");


		}

		private void runContainsAllElement(List list2) throws IOException {

			Long startTime = System.nanoTime();
			list.containsAll(list2);
			Long endTime = System.nanoTime();
			Long ellapsedTime = endTime - startTime;

			bufferedWriter.write("<td>"+ellapsedTime.toString()+" ns</td>");


		}

		private void runEqualsMethod(List list2) throws IOException {

			Long startTime = System.nanoTime();
			for (int i = 0; i < list2.size(); i++) {
				list.equals(list2.get(i));
			}
			Long endTime = System.nanoTime();
			Long ellapsedTime = endTime - startTime;

			bufferedWriter.write("<td>"+ellapsedTime.toString()+" ns</td>");


		}

		private void runGetMethod(List list2) throws IOException {

			Long startTime = System.nanoTime();
			for (int i = 0; i < list2.size(); i++) {
				list.get(i);
			}
			Long endTime = System.nanoTime();
			Long ellapsedTime = endTime - startTime;

			bufferedWriter.write("<td>"+ellapsedTime.toString()+" ns</td>");

		}

		private void runIndexOfMethod() throws IOException {

			Long startTime = System.nanoTime();
			list.indexOf(1);
			Long endTime = System.nanoTime();
			Long ellapsedTime = endTime - startTime;

			bufferedWriter.write("<td>"+ellapsedTime.toString()+" ns</td>");


		}

		private void runLastIndexOfMethod() throws IOException {

			Long startTime = System.nanoTime();
			list.lastIndexOf(2);
			Long endTime = System.nanoTime();
			Long ellapsedTime = endTime - startTime;
			
			bufferedWriter.write("<td>"+ellapsedTime.toString()+" ns</td>");


		}

		private void runSortMethod() throws IOException {

			Long startTime = System.nanoTime();
			list.sort((e1, e2) -> e1.compareTo(e2));
			Long endTime = System.nanoTime();
			Long ellapsedTime = endTime - startTime;

			bufferedWriter.write("<td>"+ellapsedTime.toString()+" ns</td>");


		}

		public <T> void analize(Class<T> clazz, Integer value) {

			List<Integer> list1 = Arrays.asList(new Integer[value]);

			try {
				
				bufferedWriter.write("<tr><td>"+clazz.getName()+"  Data size: "+value.toString()+"</td>");
				clazz1 = clazz;
				list = (List) clazz1.newInstance();

				runAdd(value);
				runGetMethod(list1);
				runSortMethod();
				runIndexOfMethod();
				runLastIndexOfMethod();
				runInsertAnElement(value);
				runAddAll(list1);
				runAddAllToSpecificIndex(list1, 1);
				runContainsAnElement(list1);
				runContainsAllElement(list1);
				runEqualsMethod(list1);
				runClear();

				bufferedWriter.write("\t");

			} catch (IllegalAccessException | InstantiationException e) {
				logger.info(e.getMessage());
			} catch (IOException ioe) {
				logger.log(Level.SEVERE, ioe.getMessage());

			}
		}

		public void openTableStatistic() {
			try {
				
				bufferedWriter.write("<html><body>"+"<link rel="+"stylesheet"+" href="+"index.css>"+"<table>");
				bufferedWriter.write(
						"<tr>" + "<th>Implementator Name </th>" +"<th>Add Method</th>" + "<th> Get Method</th>" + "<th>Sort Method</th>" + ""
								+ "<th>Index Of Method</th><th>Last Index Of Method</th><th>Insert an element method </th>"
								+ "<th>Add all method</th><th>Add al to specific index </th><th>Contains method</th><th>Contains all method</th><th>Equals Method</th><th>Clear Method</th></tr>");

			} catch (IOException e) {
				logger.log(Level.SEVERE, e.getMessage());
			}
		}

		public void closeTableStatistic() {

			try {
				bufferedWriter.write("</table></body></html>");

				if (bufferedWriter != null) {
					bufferedWriter.close();
				}

			} catch (IOException e) {
				logger.log(Level.SEVERE, e.getMessage());
			}

		}
	}

