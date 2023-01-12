import java.util.Scanner;

public class Main {
    public static String[] dataToDo = new String[10];
    public static java.util.Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        viewShowToDoList();
    }

    /**
     * Show all todolist
     */
    public static void showTodoList(){
        for(var i = 0; i < dataToDo.length; i++){
            var data = dataToDo[i];
            var todoNum = i + 1;

            if(data != null){
                System.out.println(todoNum + ". " + data);
            }
        }
    }

    /**
     * Adding new task to todolist
     */
    public static void addTodoList(String todo){
        // Validating dataToDo to check is there still any left space for new data
        var isFull = true;
        for (var i = 0; i < dataToDo.length ; i++) {
            if(dataToDo[i] == null){
                isFull = false;
                break;
            }
        }

        // Upsizing 2 times for the dataToDo array size
        if(isFull){
            String[] temp = dataToDo;
            dataToDo = new String[dataToDo.length * 2];
            for (var i = 0; i < temp.length ; i++) {
                dataToDo[i] = temp[i];
            }
        }

        // Adding data to the dataToDo array and checking which index is null
        for (var i = 0; i < dataToDo.length ; i++) {
            if(dataToDo[i] == null){
                dataToDo[i] = todo;
                break;
            }
        }

    }

    /**
     * Removing task from todolist
     */
    public static boolean deleteToDoList(Integer todoNum){
        if((todoNum - 1) >= dataToDo.length){
            return false;
        } else if (dataToDo[todoNum - 1] == null) {
            return false;
        } else {
            for (var i = (todoNum - 1); i < dataToDo.length; i++) {
                if(i == (dataToDo.length - 1)){
                    dataToDo[i] = null;
                }else{
                    dataToDo[i] = dataToDo[i + 1];
                }
            }
            return true;
        }
    }

    /**
     * Updating ToDo
     */
    public static boolean updateTodoList(Integer todoNum, String newTodo){
        if((todoNum - 1) >= dataToDo.length){
            return false;
        }else if(newTodo == null){
            return false;
        }
        else{
            dataToDo[todoNum - 1] = newTodo;
            return true;
        }
    }



    public static String inputTodo(String info){
        System.out.print(info + ": ");
        String todo = scanner.nextLine();
        return todo;
    }

    public static void dividerTitle(String title){
        String titleMenu = title;
        StringBuilder dividerTemp = new StringBuilder();
        for(int i = 0; i<titleMenu.length(); i ++){
            dividerTemp.append("=");
        }
        String divider = dividerTemp.toString();
        System.out.println("\t\t" + divider + "\n\t\t" + title + "\n\t\t" + divider);
    }
    /**
     * View for Show Todolist menu
     */

    public static void viewShowToDoList(){

        while (true){
            dividerTitle("TODO LIST");
            showTodoList();
            System.out.println("Menu: ");
            System.out.println("1. Add");
            System.out.println("2. Update");
            System.out.println("3. Delete");
            System.out.println("0. Exit");

            var input = inputTodo("Choose the number only");
            if (input.equals("1")){
                viewAddTodoList();
            } else if (input.equals("2")) {
                viewUpdateTodoList();
            }else if (input.equals("3")) {
                viewDeleteTodoList();
            } else if (input.equals("0")) {
                break;
            } else{
                System.out.println("Please input correct option");
            }
        }
    }

    /**
     * View for Add Todolist menu
     */
    public static void viewAddTodoList(){
        dividerTitle("ADD NEW TODO");
        var todo = inputTodo("ToDo (Type 0 for Cancel)");

        if (todo.equals("0")){
            // return to main menu
        } else{
            addTodoList(todo);
        }

    }

    /**
     * View for Update TodoList
     */
    public static void viewUpdateTodoList(){
        dividerTitle("UPDATE TODO");
        showTodoList();
        while (true){
            var todoNum = inputTodo("Type the ToDo Number (Type 0 for Cancel)");
            if(todoNum.equals("0")){
                break;
            }
            var newTodo = inputTodo("Type the new ToDo (Type 0 for Cancel)");
            if(newTodo.equals("0")){
                break;
            }
            else{
                boolean isSuccess = updateTodoList(Integer.valueOf(todoNum), newTodo);
                if(!isSuccess){
                    System.out.println("Failed to update ToDo");
                    break;
                }
                break;
            }
        }
    }

    /**
     * View for Remove Todolist
     */
    public static void viewDeleteTodoList(){
        dividerTitle("REMOVE TODO");
        showTodoList();
        var todoNumber = inputTodo("Type the ToDo Number (Type 0 for Cancel)");

        if (todoNumber.equals("0")){
            // return to main menu
        } else{
            boolean isSuccess = deleteToDoList(Integer.valueOf(todoNumber));
            if(!isSuccess){
                System.out.println("Failed Deleting ToDo");
            }
        }
    }
}
