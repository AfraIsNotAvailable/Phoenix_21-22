package org.firstinspires.ftc.teamcode;



import com.qualcomm.robotcore.hardware.DcMotor;


import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Omnidirectional {




    //tine evidenta de axa a si b din schema de pe
    //research paper, pt interesati contactatima da
    //ce trb sa stiti ii ca basically, a ii distanta orizontala
    //parca de la centru de greutate la centru unei roti, iar b
    //distanta verticala. trebuie cunoscute pt calcule ulterioare

    private static class AXIS{
        public static float A = 1;
        public static float B = 1; //???
    }


    //clasa care sa tina evidenta de fiecare motor,
    //motivu pt care ii clasa e ca arata mai furmus
    private static class Motors{


        //vitezele unghiulare la fiecare roata
        public static float W1 = 0;
        public static float W2 = 0;
        public static float W3 = 0;
        public static float W4 = 0;

        //puterile la fiecare motor 0 <= Pn <= 1
        public static float P1 = 0;
        public static float P2 = 0;
        public static float P3 = 0;
        public static float P4 = 0;

        public static boolean angularCalculated = false;
        public static boolean powersCalculated = true;

        //viteza unghiulara maxima dintre restu
        //trb pt a da ulterior power la motoare
        //da not really idk asa era in cod eu doar
        //dau refactor nu sterg decat ce-i useless
        public static float maxAngularVel()
        {
            if(W1 <= W2 && W1 <= W3 && W1 <= W4)
                return W1;
            if(W2 <= W1 && W2 <= W3 && W2 <= W4)
                return W2;
            if(W3 <= W1 && W3 <= W2 && W3 <= W4)
                return W2;
            if(W4 <= W1 && W4 <= W2 && W4 <= W3)
                return W2;
            return W1;
        }

        //transforma vitezele ungiulare in puteri
        //cuprinse in I = [0,1], sau intre [0,lim]
        //unde lim cuprins inte [0,1]
        public static void calculatePowers(float lim) throws Exception {
            //dam excpetie daca nu avem calculate veiteze noi
            if(!angularCalculated)
                throw new Exception("OmniCalculationError: Angular velocities not calculated before recalculating powers.");

            float maxW = maxAngularVel();

            Motors.P1 = lim * (Motors.W1 / maxW);
            Motors.P2 = lim * (Motors.W2 / maxW);
            Motors.P3 = lim * (Motors.W3 / maxW);
            Motors.P4 = lim * (Motors.W4 / maxW);

            angularCalculated = false;
            powersCalculated = true;
        }

        public static void setPowers() throws Exception{
            if(!powersCalculated)
                throw new Exception("OmniCalculationError: Powers not calculated."); // nu putem da putere la motoare daca nu s-o calculat puteri

            PowerManager.setTargets(P1,P2,P3,P4);

            angularCalculated = false;
            powersCalculated = false;
        }

        public static void kill() {
            Robot.Motors.LF.setPower(0);
            Robot.Motors.RF.setPower(0);
            Robot.Motors.LB.setPower(0);
            Robot.Motors.RB.setPower(0);
        }
    }

    //again, e nevoie de raza rotii pt calcule
    //nu pot explica mai mult it's literally physics
    private static float wheelRadius;

    /*
    * DEPRECATED
    * */
    //what does this do
//    public static void SetMotors(DcMotor rb, DcMotor rf, DcMotor lf, DcMotor lb)
//    {
//        Robot.Motors.LB = lb;
//        Robot.Motors.LF = lf;
//        Robot.Motors.RF = rf;
//        Robot.Motors.RB = rb;
//    }


    //un fel de constructor da nu chiar ca ii clasa statica, OOP elitists would kill me
    public static void Init(float A, float B, float wheelRadius)
    {
        Omnidirectional.wheelRadius = wheelRadius;
        AXIS.A = A;
        AXIS.B = B;
    }

    // calculeaza viteza unghiulara pt a se aplica forta V si rotatia W
    // le baga in Motors, unde se tine evidenta datelor fiecarui motor
    // Vx si Vy is forta descompusa
    public static void calculateAngulars(float Vx, float Vy, float W)
    {
        Motors.W1 = Vx - Vy - (AXIS.A + AXIS.B) * W;
        Motors.W2 = Vx + Vy + (AXIS.A + AXIS.B) * W;
        Motors.W3 = Vx + Vy - (AXIS.A + AXIS.B) * W;
        Motors.W4 = Vx - Vy + (AXIS.A + AXIS.B) * W;

        Motors.W1 *= 1/wheelRadius;
        Motors.W2 *= 1/wheelRadius;
        Motors.W3 *= 1/wheelRadius;
        Motors.W4 *= 1/wheelRadius;

        Motors.angularCalculated = true;
        Motors.powersCalculated = false;

    }

    //aceeasi chestie doar ca acm avem parametru de Vector2D pentru forta
    public static void calculateAngulars(Vector2D f, float W)
    {
        Motors.W1 = f.x - f.y - (AXIS.A + AXIS.B) * W;
        Motors.W2 = f.x + f.y + (AXIS.A + AXIS.B) * W;
        Motors.W3 = f.x + f.y - (AXIS.A + AXIS.B) * W;
        Motors.W4 = f.x - f.y + (AXIS.A + AXIS.B) * W;

        Motors.W1 *= 1/wheelRadius;
        Motors.W2 *= 1/wheelRadius;
        Motors.W3 *= 1/wheelRadius;
        Motors.W4 *= 1/wheelRadius;
    }

    //urmeaza sase implementari diferite ale lui set velocity cu scopu
    //de a acoperii toate cazurile de care maybe ai nevoie idk????????

    public static void setVelocity(float Vx, float Vy) throws Exception {
        calculateAngulars(Vx,Vy,0);
        Motors.calculatePowers(1.f);
        Motors.setPowers();
    }

    public static void setVelocity(float Vx, float Vy, float W) throws Exception {
        calculateAngulars(Vx,Vy,W);
        Motors.calculatePowers(1.f);
        Motors.setPowers();
    }

    public static void setVelocity(float Vx, float Vy, float W, float limit) throws Exception {
        //explic numa aici ca dupa is intuitive

        //se calculeaza intai vitezele angulare pt targetu V si W,
        // anterior sunt cazuri in care W nu e
        // necesar/ nu e limita la putere, d
        // eci hardcode-uim valori aferente
        calculateAngulars(Vx,Vy,W);

        //calculam puterea cu limita data, gen sa fie puterea maxima
        //la un motor 0.8 daca limita ii 0.8, si restu puterilor sa fie in
        //raport cu puterea maxima
        Motors.calculatePowers(limit);

        //aplicam puterile calculate
        Motors.setPowers();
    }

    public static void setVelocity(Vector2D V) throws Exception {
        calculateAngulars(V,0);
        Motors.calculatePowers(1.f);
        Motors.setPowers();
    }

    public static void setVelocity(Vector2D V, float W) throws Exception {
        calculateAngulars(V,W);
        Motors.calculatePowers(1.f);
        Motors.setPowers();
    }

    public static void setVelocity(Vector2D V, float W, float limit) throws Exception {
        calculateAngulars(V,W);
        Motors.calculatePowers(limit);
        Motors.setPowers();
    }

    public static void setVelocity(float Vx, float Vy, int ms) throws Exception{
        //incepem un task now pe thread care zice cv de genu
        // ba in ms milisecunde tai curentu la motoare :*
        Robot.scheduler.schedule(Motors::kill, ms,TimeUnit.MILLISECONDS); //de parca ne permitem microseconds lol

        calculateAngulars(Vx,Vy,0);
        Motors.calculatePowers(1.f);
        Motors.setPowers();
    }

    public static void setVelocity(float Vx, float Vy,float W, int ms) throws Exception{
        //incepem un task now pe thread care zice cv de genu
        // ba in ms milisecunde tai curentu la motoare :*
        Robot.scheduler.schedule(Motors::kill, ms,TimeUnit.MILLISECONDS); //de parca ne permitem microseconds lol

        calculateAngulars(Vx,Vy,W);
        Motors.calculatePowers(1.f);
        Motors.setPowers();
    }

    public static void setVelocity(float Vx, float Vy, float W, float limit, int ms) throws Exception{
        //incepem un task now pe thread care zice cv de genu
        // ba in ms milisecunde tai curentu la motoare :*
        Robot.scheduler.schedule(Motors::kill, ms,TimeUnit.MILLISECONDS); //de parca ne permitem microseconds lol

        calculateAngulars(Vx,Vy,W);
        Motors.calculatePowers(limit);
        Motors.setPowers();
    }

    public static void setVelocity(float Vx, float Vy, Runnable condition) throws Exception{
        //incepem un task now pe thread care asteapta frozen
        //pana cand conditia de exit (condition) retunreaza true
        Robot.scheduler.schedule(condition, 0,TimeUnit.MILLISECONDS); //de parca ne permitem microseconds lol

        calculateAngulars(Vx,Vy,0);
        Motors.calculatePowers(1.f);
        Motors.setPowers();
    }

    public static void setVelocity(float Vx, float Vy,float W, Runnable condition) throws Exception{

        //incepem un task now pe thread care asteapta frozen
        //pana cand conditia de exit (condition) retunreaza true
        Robot.scheduler.schedule(condition, 0,TimeUnit.MILLISECONDS); //de parca ne permitem microseconds lol

        calculateAngulars(Vx,Vy,W);
        Motors.calculatePowers(1.f);
        Motors.setPowers();
    }

    public static void setVelocity(float Vx, float Vy, float W, float limit, Runnable condition) throws Exception{
        //incepem un task now pe thread care asteapta frozen
        //pana cand conditia de exit (condition) retunreaza true
        Robot.scheduler.schedule(condition, 0,TimeUnit.MILLISECONDS); //de parca ne permitem microseconds lol

        calculateAngulars(Vx,Vy,W);
        Motors.calculatePowers(limit);
        Motors.setPowers();
    }

}
