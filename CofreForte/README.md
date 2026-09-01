# Missão 04 - Cofre Forte (JavaBank)

## Estrutura
```
src/
└── br/com/banco/
    ├── Agencia.java
    ├── Cliente.java
    ├── ContaBancaria.java
    └── main/
        └── MainTeste.java
```

## Como rodar no VS Code
1. Instale a extensão "Extension Pack for Java" (Microsoft).
2. Abra esta pasta (CofreForte) no VS Code.
3. Abra o arquivo `src/br/com/banco/main/MainTeste.java`.
4. Clique em "Run" acima do método `main` (ou F5).

## Como rodar pelo terminal
```
cd CofreForte
javac -d bin $(find src -name "*.java")
java -cp bin br.com.banco.main.MainTeste
```

## Como versionar (git)
```
git init
git add .
git commit -m "Missao 04: Cofre Forte - implementacao POO"
git remote add origin <URL_DO_SEU_REPOSITORIO>
git push -u origin main
```
