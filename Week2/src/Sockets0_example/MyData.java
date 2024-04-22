package Sockets0_example;

import java.io.Serializable;

class MyData implements Serializable
{
    private String data;
    private int counter;

    public MyData(String data, int counter) {
        this.data = data;
        this.counter = counter;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    @Override
    public String toString() {
        return "ObjectStreamDemo.MyData{" +
                "data='" + data + '\'' +
                ", counter=" + counter +
                '}';
    }


}