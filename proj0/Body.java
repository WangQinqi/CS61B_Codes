public class Body {
  public double xxPos;
  public double yyPos;
  public double xxVel;
  public double yyVel;
  public double mass;
  public String imgFileName;
  //Gravity Constant
  public static final double G = 6.67e-11;

  //Constructors
  public Body(double xP, double yP, double xV, double yV, double m, String img) {
    xxPos = xP;
    yyPos = yP;
    xxVel = xV;
    yyVel = yV;
    mass = m;
    imgFileName = img;
  }

  public Body(Body b) {
    xxPos = b.xxPos;
    yyPos = b.yyPos;
    xxVel = b.xxVel;
    yyVel = b.yyVel;
    mass = b.mass;
    imgFileName = b.imgFileName;
  }

  //Functions
  public double calcDistance(Body other_one) {
    double delta_x = this.xxPos - other_one.xxPos;
    double delta_y = this.yyPos - other_one.yyPos;
    double R = java.lang.Math.pow(delta_x, 2) + java.lang.Math.pow(delta_y, 2);
    return java.lang.Math.sqrt(R);
  }

  public double calcForceExertedBy(Body other_one) {
    double R = java.lang.Math.pow(calcDistance(other_one), 2);
    double F = G * this.mass * other_one.mass / R;
    return F;
  }

  public double calcForceExertedByX(Body other_one) {
    double F = calcForceExertedBy(other_one);
    double R = calcDistance(other_one);
    double F_x = F * (other_one.xxPos - this.xxPos) / R;
    return F_x;
  }

  public double calcForceExertedByY(Body other_one) {
    double F = calcForceExertedBy(other_one);
    double R = calcDistance(other_one);
    double F_y = F * (other_one.yyPos - this.yyPos) / R;
    return F_y;
  }

  public double calcNetForceExertedByX(Body[] others) {
    double F_x = 0;
    for(int i=0; i<others.length; i++)
      if (!others[i].equals(this))  //planets do not exert force on themselves
        F_x += calcForceExertedByX(others[i]);
    return F_x;
  }

  public double calcNetForceExertedByY(Body[] others) {
    double F_y = 0;
    for(int i=0; i<others.length; i++)
      if (!others[i].equals(this))
        F_y += calcForceExertedByY(others[i]);
      return F_y;
  }

  public void update(double dt, double fX, double fY) {
    double a_x = fX / this.mass;
    double a_y = fY / this.mass;
    double v_x_new = this.xxVel + dt * a_x;
    double v_y_new = this.yyVel + dt * a_y;
    double p_x_new = this.xxPos + dt * v_x_new;
    double p_y_new = this.yyPos + dt * v_y_new;
    this.xxVel = v_x_new;
    this.yyVel = v_y_new;
    this.xxPos = p_x_new;
    this.yyPos = p_y_new;
  }

  //self-drawing
  public void draw() {
    String imageToDraw = "images/" + imgFileName;
    StdDraw.picture(this.xxPos, this.yyPos, imageToDraw);
    //StdDraw.show();
  }
}
