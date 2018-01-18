import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ErdosNumber {

	public static void main(String[] args) throws IOException {
		
//		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		
		BufferedReader br = new BufferedReader (new FileReader ("C:\\Users\\yy957\\Desktop\\ACM\\Main\\inputs\\p10044.txt"));
		
		int n = Integer.parseInt(br.readLine());
		while (n-- > 0) {					
			String s[] = br.readLine().split(" ");
			int P = Integer.parseInt(s[0]);
			int N = Integer.parseInt(s[1]);
			
			List<String> authors = new ArrayList<>();
			for (int i = 0; i < P; i++) {
				
				String line = br.readLine();
				String[] linePart1 = line.split(":");
				String[] temp_list = linePart1[0].split(", ");
				for (int j = 0; j < temp_list.length; j += 2) {
					String full_name = (temp_list[j] + ',' + temp_list[j+1]);
					System.out.println(full_name);
				}
			}
			
			String[] nameList = new String[N]; 
			for (int i = 0; i < N; i++) {
				nameList[i] = br.readLine();
			}
			
		}
	}

}
