package BasicLambdas;

public class Person {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getHeigth() {
        return heigth;
    }

    public void setHeigth(double heigth) {
        this.heigth = heigth;
    }

    private int age;
    private double heigth;
    public Person(String name, int age, double heigth) {
        this.name = name;
        this.age = age;
        this.heigth = heigth;
    }

    public static boolean isAdult(Person p) {
        return p.age>18 ? true : false;
    }

    public String toString(){
        return  "BasicLambdas.Person{" + "age=" + age + ", name=" + name + ", height=" + heigth + '}';
    }
}
