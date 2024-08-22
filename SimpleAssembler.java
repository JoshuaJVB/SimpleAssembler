import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SimpleAssembler {
    public static Map<String, Integer> interpret(String[] program){
        boolean running = true;
        int index = 0;
        HashMap<String, Integer> holder = new HashMap<>();
        String[] tokens;
        boolean isValid = false;

        while(running == true){
            tokens = program[index].split(" ");
            switch (tokens[0]) {
                case "mov":
                    isValid = false; 
                    for(int i = 0; i < tokens[2].length(); i++){
                        isValid = Character.isLetter(tokens[2].charAt(i));
                        if(isValid == false){
                            i = tokens[2].length() +1;
                        }
                    }
                    if(isValid == false){
                        holder.put(tokens[1], Integer.parseInt(tokens[2]));
                    }else{
                        holder.put(tokens[1], holder.get(tokens[2]));
                    }
                    if(Objects.isNull(holder.get(tokens[1]))){
                        holder.put(tokens[1], 0);
                    }
                    index++;
                    break;
                case "inc":
                    holder.put(tokens[1], holder.get(tokens[1]) + 1);
                    index++;
                    break;
                case "dec":
                holder.put(tokens[1], holder.get(tokens[1]) - 1);
                    index++;
                    break;
                case "jnz":
                    boolean var1 = false;
                    boolean var2 = false;
                    boolean num1 = false;
                    boolean num2 = false;

                    try {
                        Integer.parseInt(tokens[1]);
                        num1 = true;
                    } catch (Exception e) {
                        try {
                            holder.get(tokens[1]);
                            var1 = true;
                        } catch (Exception e1) {
                            tokens[1] = "0";
                            num1 = true;
                        }
                    }

                    try {
                        Integer.parseInt(tokens[2]);
                        num2 = true;
                    } catch (Exception e) {
                        try {
                            holder.get(tokens[2]);
                            var2 = true;
                        } catch (Exception e1) {
                            tokens[2] = "0";
                            num1 = true;
                        }
                    }

                    if(num1 == true && num2 == true){
                        if(Integer.parseInt(tokens[1]) != 0){
                            index += Integer.parseInt(tokens[2]);
                        }else{
                            index++;
                        }
                    }else if(num1 == true && var2 == true){
                        if(Integer.parseInt(tokens[1]) != 0){
                            try{
                                index+= holder.get(tokens[2]);
                            } catch (Exception e){
                                index++;
                            }
                        }
                    }else if(var1 = true && num2 == true){
                        try {
                            if(holder.get(tokens[1]) != 0){
                                index += Integer.parseInt(tokens[2]);
                            }else{
                                index++;
                            }
                        } catch (Exception e) {
                            index++;
                        }
                    }else if(var1 == true && var2 == true){
                        try {
                            if(holder.get(tokens[1]) != 0){
                                index+= holder.get(tokens[2]);
                            }
                        } catch (Exception e) {
                            index++;
                        }
                    }

                    break;
                default:
                    break;
            }
            if(index >= program.length){
                running = false;
            }
        }
        return holder;
    }
  }