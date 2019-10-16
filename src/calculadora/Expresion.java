/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadora;

import java.util.Scanner;

/**
 *
 */
public class Expresion {

    /**
    * <pre>
    * Clase Expresion
    *
    * Se usa para modificar expresiones aritm�ticas.
    * Puede convertir expresiones infijas a postfijas
    * Puede evaluar expresiones postfijas.
    * Puede checar errores matem�ticos o de sint�xis en una expresi�n aritm�tica.
    * </pre>
   */ 
    public Expresion() {
    }
    
    /**
    * Evalua expresiones postfijas que recibe en forma de String
    * @param expresionPostfija Una expresion aritm�tica en formato postfijo
    * @return <ul>
    * <li>El resultado de la expresi�n si logra evaluarla correctamente</li>
    * <li>0.0 si no es as�</li>
    * </ul>
    */ 
    public static String evaluarPostfija(String expresionPostfija) {
        
        Scanner scanner = new Scanner(expresionPostfija);
        
        //Se crea una pila auxiliar para guardar los numeros que se acumulan
        PilaArreglo<Double> pilaAuxiliar = new PilaArreglo();
        
        //Se comienza a evaluar considerando si el siguiente elemento es un n�mero o un operador
        while (scanner.hasNext()) {
            //Si resulta ser un n�mero, se mana a la pila auxiliar
            if (scanner.hasNextDouble()) {
                pilaAuxiliar.push(scanner.nextDouble());
            }
            //Si no es un n�mero, es un operador
            //Si es un operador, se evalua la operaci�n con los �ltimos dos elementos de la pila (usando pop)
            else {
                String operador = scanner.next();
                double valor1 = pilaAuxiliar.pop();
                double valor2 = pilaAuxiliar.pop();
                
                switch (operador) {
                case "+" : pilaAuxiliar.push(valor2 + valor1); break;
                case "-" : pilaAuxiliar.push(valor2 - valor1); break;
                case "*" : pilaAuxiliar.push(valor2 * valor1); break;
                case "/" : pilaAuxiliar.push(valor2 / valor1); break;
                }
            }
        }

        //Se intenta regresar el �ltimo elemento en la pila que deber�a de ser el resultado de la expresion aritm�tica postfija
        try{return Double.toString(pilaAuxiliar.pop());}
        //Si no se logra, se regresa 0.0
        catch(Exception e0){return "0.0";}
    }
    
    
    /**
     * <pre>
     *      Regresa una expresi�n matem�tica convertida a la notaci�n postfija: 
     *              Notaci�n infija: a-b*c   
     *              Notaci�n postfija: abc*-
     * 
     *      @param expresionInfija: Recibe una expresi�n matem�tica en su forma infija, la cual se hace pasar primero por el detector de errores.
     *      @return postfijo: Regresa en forma de String la expresi�n convertida, donde cada elemento est� separado por un espacio.
     * 
     * </pre>
     */
    public static String convertirPostfija(String expresionInfija){
        PilaArreglo res= new PilaArreglo();         //Ambas pila res y aux sirven para ir formando la expresi�n convertida, mientras que op guardar� los operadores.
        PilaArreglo aux = new PilaArreglo();
        PilaArreglo<Character> op= new PilaArreglo();       
        int i=0;
        int j;
        char c;
        String cifra;
        boolean b;
        String postfijo="";
        
        if(expresionInfija.charAt(0)=='+'){
            i++;
        }
        
        //A continuaci�n se checan algunas excepciones dif�ciles de tratar generalmente

        expresionInfija=expresionInfija.replace("-+","-");
        expresionInfija=expresionInfija.replace("+-","-");
        
        while(i<expresionInfija.length()){
            c=expresionInfija.charAt(i);
            
            //Para extraer del String las cifras se recurri� al equivalente de los n�meros en ascii, para facilitar su obtenci�n.
            if((int)c>=48&&(int)c<=57||c=='.'){     
                j=i+1;
                b=true;
                
                //Mientras se traten de n�meros pertenecientes a una sola cifra, el String ira recorriendo uno a uno y finalmente los extraera en su conjunto y agregar� a la pila res.
                while(j<expresionInfija.length()&&b){        
                    if((int)expresionInfija.charAt(j)>=48&&(int)expresionInfija.charAt(j)<=57||expresionInfija.charAt(j)=='.'){
                       j++; 
                    }
                    else{
                        b=false;
                    }
                }
                if(j>=expresionInfija.length()){
                    cifra=expresionInfija.substring(i);
                }
                else{
                    cifra=expresionInfija.substring(i,j);
                }
                
                res.push(cifra);
                i=j-1;

            }
            else{
                /*
                *A continu�cion se repasan los casos en que se encuentren par�ntesis, si es uno que abre se agregar� a la pila op, 
                *por el contrario si cierra se extrer�n los operadores que van contenidos dentro del par�ntesis y se eliminar� su hom�logo,
                *de esta forma la expresi�n postfija no necesit� de par�ntesis.
                */
                if(c=='('){
                    op.push(c);
                }
                else{
                    if(c==')'){
                        while(!op.isEmpty()&&op.peek()!='('){
                            res.push(op.pop());
                        }
                        
                        if(!op.isEmpty()&&op.peek()=='('){
                            op.pop();
                        }
                    }
                    else{
                        
                        /*
                        *Para los operadores, �stos se iran agregando o extrayendo seg�n las reglas de jerarqu�a,
                        *si el que encuentra es de menor o igual jerarqu�a a los anteriores, estos se sustraeran y al final se agregar� �ste.
                        */
                        if(c=='+'||c=='-'){
                            while(!op.isEmpty()&&op.peek()!='('){
                                res.push(op.pop());
                            }
                        }
                        else{
                            b=true;
                            while(!op.isEmpty()&&op.peek()!='('&&b){
                                if(op.peek()=='*'||op.peek()=='/'){
                                    res.push(op.pop());
                                }
                                else{
                                    b=false;                                
                                }
                            }
                        }
                        op.push(c);
                    }
                }
           }
        
            i++;
        }
        
        while(!op.isEmpty()){
            res.push(op.pop());
        }
        
        while(!res.isEmpty()){
            aux.push(res.pop());
        }
        // Al formarse la expresi�n en la pila res, se recurre a la pila aux para que al ir extrayendo los elementos y agregarlos al String queden ordenados.
        while(!aux.isEmpty()){
            postfijo+= aux.pop() + " ";
        }
        
        return postfijo;
    }
    
    
    /**<pre>
     * Revisa la sintaxis de una expresi�n matem�tica
     * que �nicamente usa las 4 operaciones aritm�ticas
     * b�sicas.
     * </pre>
     * @param prob Expresi�n matem�tica a revisar
     * @return <ul>
     *      <li>true: si la sintaxis de la expresi�n es correcta</li>
     *      <li>false: si la sintaxis de la expresi�n es incorrecta</li>
     * </ul>
     */
    public static boolean detectorErroresSintaxis(String prob){
        boolean resp = true;
        boolean band = true;
        char pa;
        PilaArreglo<Character> p= new PilaArreglo<Character>();
        int i = 0;
        int j;
        int f = prob.length() - 1;
        
        //verifico que el ultimo caracter de la expresi�n no sea un operador o un parentesis de cierre
        if(prob.charAt(f) == '+' || prob.charAt(f) == '-' || prob.charAt(f) == '*' || prob.charAt(f) == '/' || prob.charAt(f) == '('){
            resp = false;// en caso de serlo, la respuesta es false
        }
        
        //comienso a revisar la expresi�n desde el inico buscando operadores + y -, ya que estos no afectan al inico
        //dejo de revisar di encuntro un n�mero o un operador diferente a + y - 
        while(i < prob.length() && resp && band){
            //verifico si el termino es un *,/ o )
            if(prob.charAt(i) == '*' || prob.charAt(i) == '/' || prob.charAt(i) == ')'){
                resp = false;// en caso de serlo la respuesta es false
            } else if(prob.charAt(i) == '.'){//si no fue lo anterior verifico si es un punto
                //si si es un punto reviso si el siguiente es otro punto o un operador
                if(prob.charAt(i+1) == '.' || prob.charAt(i) == '*' || prob.charAt(i) == '/' || prob.charAt(i) == '+' || prob.charAt(i) == '-'){
                    resp = false;//en caso de serlo la respuesta es false
                } else {//si no fue nada de lo anterior entonses debe ser un n�mero o un parentesis
                    band = false;//salgo del while
                }
            } else if( prob.charAt(i) != '+' && prob.charAt(i) != '-'){//si no fue nada de lo anterior reviso que no sea ni un + ni un -
                band = false;//si no es etonses salgo del while
            } else {
                i++;// si si es un + o un -, aumeto 1 a la i para revisar el siguiente
            }
        }
        
        //si al final se salio del while por que recorrio toda la cadena entonses quiere decir
        //que la expresi�n solo tiene operadores + y -
        if(i > prob.length()){
            resp = false;// si es el caso la respuesta es false
        }
        
        //si no fue el caso anterior entonses quiere decir que encontro un n�mero o un parentesis
        //prosedo a revisar que los parentesis esten bien balanseados y que no haya otros errores de
        //sintaxis
        while(i<prob.length() && resp){
                    
            pa=prob.charAt(i);
            
            if(pa=='('){
                p.push(pa);
            }
            else{
                switch (pa){
                    case ')':
                        if(!p.isEmpty()&&p.peek()=='('){
                            p.pop();
                        }
                        else{
                            resp=false;
                        }
                        break;
                    case '.':
                        //en caso de que el caracter altual sea un punto reviso si antes de el hay un operador
                        if(prob.charAt(i-1) == '+' || prob.charAt(i-1) == '-' || prob.charAt(i-1) == '*' || prob.charAt(i-1) == '/'){
                            //si si hay resviso hay un caracter despues
                            if((i+1) < prob.length()){
                                //si si hay reviso que es ese caracter
                                if(prob.charAt(i+1) == '.' || prob.charAt(i+1) == '+' || prob.charAt(i+1) == '-' || prob.charAt(i+1) == '*' ||prob.charAt(i+1) == '/' || prob.charAt(i+1) == '(' || prob.charAt(i+1) == ')'){
                                    resp = false;//si no es un numero la respuesta es false
                                }
                            } else {//si no hay otro caracter despues la respuesta es false
                                resp = false;
                            }
                        }
                        //si lo que esta antes no es un operador y si hay otro caracter despues
                        if((i+1) < prob.length()){
                            //reviso que hay en los caracteres siguietes, si es alguno de los siguientes casos la respuesta sera false
                            //5.2.2, 5..2, etc. 
                            if(prob.charAt(i+1) == '.'){
                            resp = false;
                            } else {
                                j = i+1;
                                band = true;
                                while(j < prob.length() && band){
                                    if(prob.charAt(j) == '+' || prob.charAt(j) == '-' || prob.charAt(j) == '*' || prob.charAt(j) == '/' || prob.charAt(j) == '(' || prob.charAt(j) == ')'){
                                        band = false;
                                    } else if (prob.charAt(j) == '.'){
                                        resp = false;
                                        band = false;
                                    }
                                    j++;
                                }
                                i = j-2;
                            } 
                        }                  
                        break;
                    //reviso los casos en los que el caracter actual es un operador
                    //solo sera false la respuesta si el siguiente caracter es otro
                    //operador
                    case '+':
                        if(prob.charAt(i+1) == '*' || prob.charAt(i+1) == '/'){
                            resp = false;
                        }
                        break;
                    case '-':
                        if(prob.charAt(i+1) == '*' || prob.charAt(i+1) == '/'){
                            resp = false;
                        }
                        break;
                    case '*':
                        if(prob.charAt(i+1) == '+' || prob.charAt(i+1) == '*' || prob.charAt(i+1) == '/'){
                            resp = false;
                        }
                        break;
                    case '/':
                        if(prob.charAt(i+1) == '+' || prob.charAt(i+1) == '*' || prob.charAt(i+1) == '/'){
                            resp = false;
                        }
                        break;
                }
            }
            
            i++;
        }
        
        if(resp&&!p.isEmpty()){
            resp=false;
        }
        
        return resp;//regreso la respuesta final
    }
    
    /**<pre>
     * Revisa las operaciones de una expresi�n matem�tica
     * que �nicamente usa las 4 operaciones aritm�ticas
     * b�sicas; busca que no se comentan erros matem�ticos
     * como unicamente se usan las 4 operaciones basicas el 
     * �nico error posible es dividir entre 0.
     * </pre>
     * @param prob Expresi�n matem�tica a revisar
     * @return <ul>
     *      <li>true: si nunca se cometio un error matem�tico(dividir entre 0)</li>
     *      <li>false: si se cometio un error matem�tico(dividir entre 0)</li>
     * </ul>
     */
    public static boolean detectorErroresMath(String prob){
        boolean resp = true;
        boolean band;
        int i = 0;
        int j;
        
        //busco en la cadena un operador de divisi�n
        while(i < prob.length() && resp){
            if(prob.charAt(i) == '/' && (i+1) < prob.length()){// caundo lo ecuentro me aseguro que exista un caracter despues
                if(prob.charAt(i+1) == '0'){//si el caracter que sigue es 0 reviso que caso es
                    if((i+2) < prob.length()){
                        j = i+2;
                        band = true;
                        while(j < prob.length() && band){
                            if(prob.charAt(j) == '+' || prob.charAt(j) == '-' || prob.charAt(j) == '*' || prob.charAt(j) == '/' || prob.charAt(j) == '(' || prob.charAt(j) == ')'){
                                resp = false; //este if revisa el caso x/000+y, la cantidad de 0 en este caso debe ser mayor igual a 1 
                                band = false; //este caso indica que hay un error
                            } else if(prob.charAt(j) == '.'){
                                band = false; //este if revisa el caso x/0.y, este caso no es un error 
                            } else if(prob.charAt(j) != '0'){
                                band = false; //este if revisa el caso x/000y, este caso tampoco debe marcar error
                            } else {
                                j++;
                            }
                        }
                        if(j < prob.length()){
                            i = j-1;
                        } else{
                            resp = false;
                        }                    
                    } else {
                        resp = false;
                    }  
                }     
            }
            i++;
        }
        
        return resp;// regresa la respuesta final
    }
}