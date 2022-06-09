package Commands;

import City.CityConstructor;

import java.util.List;

/**
 * Класс, отвечающий за хранение информации о команде.
 */

public class Information {

    /**
     * Простые аргументы.
     */

    private int simpleArguments;

    /**
     * Составные аргументы.
     */

    private int complexArguments;

    /**
     * Получает ли команда на вход колллекцию городов.
     */

    private boolean isCity;

    /**
     * Осуществляется ли командой вывод в консоль.
     */

    private boolean isPrintable;

    /**
     * Приглашение к вводу аргумента.
     */

    private List<String> description;

    /**
     * Конструктор элемента коллекции городов.
     */

    private List<Class<CityConstructor>> constructors;

    public Information(){}

    /**
     * @param simpleArguments Простые аргументы.
     * @param complexArguments Составные аргументы.
     * @param isCity Получает ли команда на вход колллекцию городов.
     * @param isPrintable Осуществляется ли командой вывод в консоль.
     * @param description Приглашение к вводу аргумента.
     * @param constructors Конструктор элемента коллекции городов.
     */

    public Information (int simpleArguments, int complexArguments, boolean isCity, boolean isPrintable, List<String> description, List<Class<CityConstructor>> constructors) {
        this.simpleArguments = simpleArguments;
        this.complexArguments = complexArguments;
        this.isCity = isCity;
        this.isPrintable = isPrintable;
        this.description = description;
        this.constructors = constructors;
    }

    /**
     * @return Простые аргументы.
     */

    public int getSimpleArguments(){
        return simpleArguments;
    }

    /**
     * @return Составные аргументы.
     */

    public int getComplexArguments(){
        return complexArguments;
    }

    /**
     * @return Получает ли команда на вход колллекцию городов.
     */

    public boolean getIsCity(){
        return isCity;
    }

    /**
     * @return Осуществляется ли командой вывод в консоль.
     */

    public boolean getIsPrintable(){
        return isPrintable;
    }

    /**
     * @return Приглашение к вводу аргумента.
     */

    public List<String> getDescription() {
        return description;
    }

    /**
     * @return Конструктор элемента коллекции городоа..
     */

    public List<Class<CityConstructor>> getConstructors() { return constructors; }

    public void setSimpleArguments(int simpleArguments) {
        this.simpleArguments = simpleArguments;
    }

    public void setComplexArguments(int complexArguments) {
        this.complexArguments = complexArguments;
    }

    public boolean isCity() {
        return isCity;
    }

    public void setCity(boolean city) {
        isCity = city;
    }

    public boolean isPrintable() {
        return isPrintable;
    }

    public void setPrintable(boolean printable) {
        isPrintable = printable;
    }

    public void setDescription(List<String> description) {
        this.description = description;
    }

    public void setConstructors(List<Class<CityConstructor>> constructors) {
        this.constructors = constructors;
    }
}
