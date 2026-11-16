package br.com.starlog.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Gerenciador de módulos com busca O(1) via HashMap.
 */
public class BaseLancamento {

    // RN09: índice associativo idModulo -> ModuloCarga
    private Map<String, ModuloCarga> modulos = new HashMap<>();

    // Cadastra o módulo usando o seu id como chave
    public void cadastrarModulo(ModuloCarga modulo) {
        this.modulos.put(modulo.getIdModulo(), modulo);
    }

    // Busca direta pela chave (tempo constante); devolve null se não existir
    public ModuloCarga buscarModulo(String idModulo) {
        return this.modulos.get(idModulo);
    }
}
