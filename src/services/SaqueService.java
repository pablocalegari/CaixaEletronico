package services;

public class SaqueService {
    // verificar se funciona depois
    public static boolean isSaqueValido(int valorSaque){
        if (valorSaque == 1 || valorSaque == 3){
            return false;
        }
        return true;
    }
}
