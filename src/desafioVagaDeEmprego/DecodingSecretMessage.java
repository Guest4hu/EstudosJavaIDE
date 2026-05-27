package desafioVagaDeEmprego;
//
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//public class DecodingSecretMessage {
//
//    private URL url;
//
//    public static void main(String[] args) {
//        DecodingSecretMessage app = new DecodingSecretMessage();
//        app.readUrl();
//
//        if (app.getUrl() == null) {
//            System.out.println("No valid URL provided. Exiting.");
//            return;
//        }
//
//        //Lê e parseia só a tabela
//        List<List<String>> table = app.readTableFromDocument();
//
//        if (table == null || table.isEmpty()) {
//            System.out.println("Failed to read table.");
//            return;
//        }
//
//        // Pula o cabeçalho (linha 0) e imprime os dados
//        for (int i = 1; i < table.size(); i++) {
//            List<String> row = table.get(i);
//            System.out.println("x=" + row.get(0) + " char=" + row.get(1) + " y=" + row.get(2));
//        }
//    }
//
//    private List<List<String>> readTableFromDocument() {
//        try {
//            // Exporta como HTML para poder parsear a tabela
//            String htmlUrl = getUrl().toString().replace("/pub", "/pub?output=html");
//            URL exportUrl = new URL(htmlUrl);
//
//            // Lê o HTML completo
//            BufferedReader reader = new BufferedReader(
//                    new InputStreamReader(exportUrl.openStream())
//            );
//            StringBuilder html = new StringBuilder();
//            String line;
//            while ((line = reader.readLine()) != null) {
//                html.append(line);
//            }
//            reader.close();
//
//            // Usa Jsoup para pegar só as linhas da tabela (<tr>)
//            Document doc = Jsoup.parse(html.toString());
//            Elements rows = doc.select("table tr");
//
//            List<List<String>> table = new ArrayList<>();
//
//            for (Element row : rows) {
//                Elements cells = row.select("td");
//                List<String> rowData = new ArrayList<>();
//                for (Element cell : cells) {
//                    rowData.add(cell.text().trim());
//                }
//                if (!rowData.isEmpty()) {
//                    table.add(rowData);
//                }
//            }
//
//            return table;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    private void readUrl() {
//        Scanner sc = new Scanner(System.in);
//        while (true) {
//            System.out.println("Write the url:");
//            String input = sc.nextLine();
//
//            if (!secureUrl(input)) {
//                System.out.println("URL must start with https://docs.google.com and end with /pub");
//                continue;
//            }
//            try {
//                setUrl(input);
//                break;
//            } catch (MalformedURLException e) {
//                System.out.println("Invalid URL. Try again.");
//            }
//        }
//        sc.close();
//    }
//
//    private boolean secureUrl(String url) {
//        return url.startsWith("https://docs.google.com") && url.endsWith("/pub");
//    }
//
//    public URL getUrl() { return url; }
//
//    public void setUrl(String url) throws MalformedURLException {
//        this.url = new URL(url);
//    }
//}