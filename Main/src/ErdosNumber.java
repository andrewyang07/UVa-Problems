import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class ErdosNumber {

	final static String ERDOS = "Erdos, P.";
	public static void main(String[] args) throws IOException {
		
//		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		
		BufferedReader br = new BufferedReader (new FileReader ("C:\\Users\\yy957\\Desktop\\ACM\\Main\\inputs\\p10044.txt"));
		
		int n = Integer.parseInt(br.readLine());	
		int senario = 1;
		while (n-- > 0) {					
			String s[] = br.readLine().split(" ");
			int P = Integer.parseInt(s[0]);
			int N = Integer.parseInt(s[1]);
			Set<String> allAuthors = new HashSet<>();
			List<List<String>> books = new ArrayList<>();
			for (int i = 0; i < P; i++) {				
				String line = br.readLine();
				String[] linePart1 = line.split(":");
				String[] temp_list = linePart1[0].split(", ");
				List<String> curtBook = new ArrayList<>();
				for (int j = 0; j < temp_list.length; j += 2) {					
					String full_name = (temp_list[j] + ", " + temp_list[j+1]);
//					System.out.println(full_name);
					curtBook.add(full_name);
						allAuthors.add(full_name);
				}
				books.add(new ArrayList<String>(curtBook));
			}
			
			Map<String, Integer> nameList = new HashMap<>();
			for (int i = 0; i < N; i++) {
				String author = br.readLine().trim();
				nameList.put(author, 0);
			}

			Map<String, Set<String>> graph = initGraph(books, allAuthors);
			Set<String> test = graph.get(ERDOS);			
			
			calculate(graph, nameList, senario++);
			for (String author : nameList.keySet()) {
				String output = "";
				int erdosNum = nameList.get(author);
				if (erdosNum > 0) {
					output = author + " " + erdosNum;
					System.out.println(output);
				}
				else {
					output = author + " infinity";
					System.out.println(output);
				}
			}
		}
	}
	
	static void calculate(Map<String, Set<String>> graph, Map<String, Integer> nameList, int scenario) {
		
		Queue<String> queue = new LinkedList<>();
		Set<String> set = new HashSet<>();
		queue.offer(ERDOS);
		int distance = 0;
		String firstLine = "Scenario " + scenario;
		System.out.println(firstLine);
		while (!queue.isEmpty()) {
			String curt = queue.poll();
			distance++;
			for (String nei : graph.get(curt)) {
				if (nameList.containsKey(nei) && nameList.get(nei) == 0) {					
					nameList.put (nei, distance);
				}
				if (set.contains(nei))
					continue;
				queue.offer(nei);
				set.add(nei);
			}
		}		
		
	}
		
	
	static Map<String, Set<String>> initGraph(List<List<String>> books, Set<String> allAuthors) {
		Map<String, Set<String>> graph = new HashMap<>();
		for (String author : allAuthors) {
			graph.put(author, new HashSet<String>());
		}
		
		for (int i = 0; i < books.size(); i++) {
			List<String> curtBook = books.get(i);
			for (String author : curtBook) {
				for (String nei : curtBook) {
					if (nei.equals(author))
						continue;
					graph.get(author).add(nei);
				}
			}
		}
		
		return graph;
	}

}
