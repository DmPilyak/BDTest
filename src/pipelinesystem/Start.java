package pipelinesystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Start {
	public static void main(String[] args) {

		System.out.println("Roure exist; Min length");
		
		String inputFile = "checkDistancePoints.csv";
		String line = "";
		String cvsSplitBy = ";";

		try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
			File inp = new File(inputFile);
			FileReader fileReaderInp = new FileReader(inp);
			LineNumberReader lineNumberReader = new LineNumberReader(fileReaderInp);
			String[] points;
			while ((line = br.readLine()) != null) {
				points = line.split(cvsSplitBy);
				new Start().calculateDistance(points[0], points[1]);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void calculateDistance(String point1, String point2) {
		String inputFile = "inputData.csv";
		String line = "";
		String cvsSplitBy = ";";

		try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
			int lineNumber = 0;
			File inp = new File(inputFile);
			FileReader fileReaderInp = new FileReader(inp);
			LineNumberReader lineNumberReader = new LineNumberReader(fileReaderInp);
			while (lineNumberReader.readLine() != null) {
				lineNumber++;
			}
			Graph graph = new Graph();
			Set<String> numNodes = new HashSet<>();
			List<Node> nodesList = null;
			String[] points = null;

			BufferedReader br2 = new BufferedReader(new FileReader(inputFile));
			while ((line = br2.readLine()) != null) {
				nodesList = new ArrayList<>();
				points = line.split(cvsSplitBy);
				numNodes.add(points[0]);
				numNodes.add(points[1]);
				Iterator<String> it = numNodes.iterator();
				while (it.hasNext()) {
					Node node1 = new Node(it.next());
					nodesList.add(node1);
				}
			}

			while ((line = br.readLine()) != null) {
				points = line.split(cvsSplitBy);
				numNodes.add(points[0]);
				numNodes.add(points[1]);

				for (int i = 0; i < lineNumber; i++) {
					getElementInArrayList(nodesList, points[0])
							.addDestination(getElementInArrayList(nodesList, points[1]), Integer.parseInt(points[2]));
					getElementInArrayList(nodesList, points[1])
							.addDestination(getElementInArrayList(nodesList, points[0]), Integer.parseInt(points[2]));
				}
			}
			for (int i = 0; i < nodesList.size(); i++) {
				graph.addNode(nodesList.get(i));
			}
			printResult(graph, point1, point2);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static Node getElementInArrayList(List<Node> list, String num) {
		for (Node node : list) {
			if (node.getName().equals(num)) {
				return node;
			}
		}
		return null;
	}

	public static boolean checkPoint(Graph graph, Node node, String firstPoint, String secondPoint) {
		new DijkstraAlgoritm().calculateShortestPathFromSource(graph, graph.getNodeByName(firstPoint));

		if (node.getName().equals(secondPoint) && node.getDistance() != Integer.MAX_VALUE) {
			return true;
		}
		return false;
	}

	public static void printResult(Graph graph, String firstPoint, String secondPoint) {
		int k = 0;
		for (Node node : graph.getNodes()) {
			if (checkPoint(graph, node, firstPoint, secondPoint)) {
				System.out.println(checkPoint(graph, node, firstPoint, secondPoint) + ";" + node.getDistance());
				k = 1;
				break;
			}
		}
		if (k == 0) {
			System.out.println("false");
		}
	}
}
