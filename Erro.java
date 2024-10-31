class pedidoInvalidoException extends Exception{
    public pedidoInvalidoException(){
        super();
    }

    @Override
    public String toString() {
        return "Nenhum prato selecionado";
    }
}
