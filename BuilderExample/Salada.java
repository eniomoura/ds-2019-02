import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Salada {

    private List<String> folhas;
    private List<String> frutas;
    private List<String> molhos;
    private List<String> temperos;

    public static class Builder {

        private List<String> folhas = new ArrayList<>();
        private List<String> frutas = new ArrayList<>();
        private List<String> molhos = new ArrayList<>();
        private List<String> temperos = new ArrayList<>();

        public Builder(final String folha) {
            folhas.add(folha);
        }


        public Builder addFolha(final String folha) {
            folhas.add(folha);
            return this;
        }


        public Builder addFruta(final String fruta) {
            frutas.add(fruta);
            return this;
        }


        public Builder addTempero(final String tempero) {
            temperos.add(tempero);
            return this;
        }


        public Builder addMolho(final String molho) {
            molhos.add(molho);
            return this;
        }

        public Salada build() {
            return new Salada(this);
        }
    }

    private Folha(Builder builder) {
        folhas = Collections.unmodifiableList(builder.folhas);
        frutas = Collections.unmodifiableList(builder.frutas);
        molhos = Collections.unmodifiableList(builder.molhos);
        temperos = Collections.unmodifiableList(builder.temperos);
    }

    public List<String> getFolhas() {
        return folhas;
    }

    public List<String> getFrutas() {
        return frutas;
    }

    public List<String> getMolhos() {
        return molhos;
    }

    public List<String> getTemperos() {
        return temperos;
    }

}
