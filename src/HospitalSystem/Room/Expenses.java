package HospitalSystem.Room;

import java.util.List;

class Expenses {
    private static class Pair {
        public String key;
        public int Value;

        Pair(String key, int Value) {
            this.key = key;
            this.Value = Value;
        }
    }

    private final List<Pair> expenses = new java.util.ArrayList<>();

    public void addExpense(String name, int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        expenses.add(new Pair(name, amount));
    }

    public int getTotalExpenses() {
        int total = 0;
        for (Pair expense : expenses) {
            total += expense.Value;
        }
        return total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("HospitalSystem.Labs.Room.Expenses{");
        for (Pair expense : expenses) {
            sb.append(expense.key).append(": ").append(expense.Value).append("\n");
        }
        sb.append('}');
        return sb.toString();
    }
}
