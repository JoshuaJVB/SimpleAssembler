public class App {
    public static void main(String[] args) {
    String[] program = new String[]{"mov a 5","inc a","dec a","dec a","jnz a -1","inc a"}; 
    String[] program2 = new String[]{"mov a -10","mov b a","inc a","dec b","jnz a -2"};


    System.out.println(SimpleAssembler.interpret(program));
    System.out.println(SimpleAssembler.interpret(program2));
    }
}
