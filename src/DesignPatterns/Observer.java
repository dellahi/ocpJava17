package DesignPatterns;

import java.util.ArrayList;
import java.util.List;

public interface Observer{
    int update();
}
interface Subject {
    void attach(Observer observer);
    void detach(Observer observer);
    void notifyObservers();
    int getState();
    void setState(int state);
}
class ConcreteObserver implements Observer{
    private Subject subject;

    public ConcreteObserver(Subject s){
        this.subject = s;
        this.subject.attach(this);
    }

    public int update(){
        return this.subject.getState();
    }
}

class ConcreteSubject implements Subject{
    private List<Observer> observers = new ArrayList<>();
    private int state;

    public ConcreteSubject(int state){
        setState(state);
    }

    public int getState() {
        return state;
    }
    @Override
    public void setState(int state) {
        this.state = state;
        notifyObservers();
    }

    public void attach(Observer observer){
        this.observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        this.observers.remove(observer);
    }

    public void notifyObservers (){
        this.observers.forEach(Observer::update);
    }
}