/**
 *
 * Az absztrakt creature osztály reprezentál egy lényt az alábbi fajtákban:
 * Homokjáró
 * Szivacs
 * Lépegető
 *
 * **/

public abstract class Creature {
    private final String name;
    int current_water;
    private int max_water;
    private boolean alive=true;
    int distance;

    public Creature (String name,int current_water){
        this.current_water=current_water;
        this.name=name;
        checkMaxWater();
    }
    public void addCurrentWater(){
    }
    public void move(){

    }
    /**
     * Az időjárás metódusok megadása hogy bizonyos időjárási fügvényében mennyi vízet iszik és mennyit kap,
     * mennyi távolságott tett meg a lényünk.
     * Ez mind fajtától és időjárástól eltér.
     * **/
    public void napos(){}
    public void felhos(){}
    public void esos(){}
/**
 * Az isAlive metódus átállítja az alive értékét hogyha elfogy a vize.
 * A lényünknek majd egy false eredménnyel tér vissza hogy él e még e a lény.
 *
 * **/
    public boolean isAlive(){
        if(current_water<=0 || !alive){
            alive=false;
            return false;
        }
        return true;
    }

    /**
     * getName visszaadja a lényünk nevét
     **/
    public String getName() {
        return name;
    }
    /**
     * getDistance visszaadja a lényünk megtett utjának mennyiségét
     **/
    public int getDistance() {
        return distance;
    }
    public void  checkMaxWater(){
    }
}
class Homokjaro extends Creature{
    private final int max_water=8;
    public Homokjaro(String name, int current_water) {
        super(name, current_water);
    }
    public  void napos(){
        if(isAlive()){
        current_water--;
        if(isAlive()){
        distance+=3;}}
    }
    public void felhos(){
        if(isAlive()){
        if(isAlive()){
        distance++;}}
    }
    public  void  esos(){
        if(isAlive()){
        current_water+=3;
        isAlive();
        checkMaxWater();}
    }
    /**
     * leelenőrzi hogy menniy vize lehet maximálisan a lényünknek a fajtájához képest.
     *
     * **/
    public void  checkMaxWater(){
        if(max_water<current_water){
            current_water=max_water;
        }
    }
}
class Szivacs extends Creature{
    private final int max_water=20;
    public Szivacs(String name, int current_water) {
        super(name, current_water);
    }
    public  void napos(){
        if(isAlive()){
        current_water-=4;isAlive();}
    }
    public void felhos(){
        if(isAlive()){
        current_water++;
        if(isAlive()){
        distance++;}
        checkMaxWater();}
    }
    public  void  esos(){
        if(isAlive()){
        current_water+=6;
        if(isAlive()){
        distance+=3;}
        checkMaxWater();}
    }
    public void  checkMaxWater(){
        if(max_water<current_water){
            current_water=max_water;
        }
    }
}

class Lepegeto extends  Creature{
    private final int max_water=12;
    public Lepegeto(String name, int current_water) {
        super(name, current_water);
    }
    public  void napos(){
        if(isAlive()){
        current_water-=2;
        if(isAlive()){
        distance++;}
        checkMaxWater();}
    }
    public void felhos(){
        if(isAlive()){
        current_water--;
        if(isAlive()){
        distance+=2;}
        isAlive();
        checkMaxWater();}
    }
    public  void  esos(){
        if(isAlive()){
        current_water+=3;
        if(isAlive()){
        distance++;}
        checkMaxWater();}
    }
    public void  checkMaxWater(){
        if(max_water<current_water){
            current_water=max_water;
        }
    }
}

