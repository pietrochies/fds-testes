public class Funcionario {

    private double salarioBruto;

    public Funcionario(double salarioBruto) {
        setSalarioBruto(salarioBruto);
    }

    public double getSalarioBruto() {
        return salarioBruto;
    }

    public void setSalarioBruto(double salarioBruto) {
        if (salarioBruto <= 0) {
            throw new IllegalArgumentException("Salário bruto inválido: deve ser maior que zero.");
        }
        this.salarioBruto = salarioBruto;
    }

    public double getINSS() {
        double base = Math.min(salarioBruto, 5000);
        return base * 0.045;
    }

    public double getIRPF() {
        if (salarioBruto <= 2500) {
            return 0;
        }
        return (salarioBruto - 2500) * 0.12;
    }

    public double getSalarioLiquido() {
        return salarioBruto - getINSS() - getIRPF();
    }
}

