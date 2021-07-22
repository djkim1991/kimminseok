import service.FileInvertingService;

public class Main {
    public static void main(String[] args) {
        // 작업 시작 시간
        final long start = System.currentTimeMillis();

        final String inputFilePath = args.length > 0 ? args[0] : "input.big";
        final String outputFilePath = args.length > 1 ? args[1] : "output.big";

        final FileInvertingService fileInvertingService = new FileInvertingService();
        fileInvertingService.read(inputFilePath);
        fileInvertingService.write(outputFilePath);

        // 작업 종료 시간
        final long end = System.currentTimeMillis();

        System.out.println("실행 시간 : " + ( end - start )/1000.0 +"초");
    }
}
