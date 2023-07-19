package DesignPatterns;

public interface Factory {
}

class ConcreteImp implements Factory{
}

class ConcreteImp2 implements Factory{
}

class ClassFactory{

    public Factory getFactory(Factory f){
        if(f instanceof ConcreteImp) return new ConcreteImp();
        else if (f instanceof ConcreteImp2) return new ConcreteImp2();
        else throw new IllegalArgumentException();
    }
}