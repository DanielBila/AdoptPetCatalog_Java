# AdoptPetCatalog_Java

Catálogo de animais (cães e gatos) com modo console e interface gráfica (Swing).

## Como executar

Importe a pasta no Eclipse e rode `petshopcatalog.Program` como Java Application, ou pelo terminal:

```
cd src
java petshopcatalog/Program.java
```

## Interface gráfica

Janela Swing para listar, adicionar e remover animais, com o total cadastrado.

- **Eclipse:** clique com o botão direito em `petshopcatalog.gui.AppGUI` → *Run As* → *Java Application*.
- **Terminal** (na raiz do projeto):

```
javac -d bin $(find src -name "*.java")
java -cp bin petshopcatalog.gui.AppGUI
```
