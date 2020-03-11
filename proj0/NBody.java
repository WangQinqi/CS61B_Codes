public class NBody {
  public static double readRadius(String filename) {
    In in = new In(filename);
    in.readInt();
    double radius = in.readDouble();
    return radius;
  }

  public static Body[] readBodies(String filename) {
    In in = new In(filename);
    int n = in.readInt();
    in.readDouble();
    Body[] bodies = new Body[n];
    for (int i=0; i<n; i++) {
      double xP = in.readDouble();
      double yP = in.readDouble();
      double xV = in.readDouble();
      double yV = in.readDouble();
      double m = in.readDouble();
      String img = in.readString();
      bodies[i] = new Body(xP, yP, xV, yV, m, img);
    }
    return bodies;
  }

  public static void main(String[] args) {
    double T = Double.valueOf(args[0]);
    double dt = Double.valueOf(args[1]);
    String filename = args[2];
    Body[] bodies = readBodies(filename);
    double radius = readRadius(filename);
    String starfiled = "images/starfield.jpg";

    /**Initialize canvas*/
    StdDraw.enableDoubleBuffering();
    StdDraw.setScale(-radius, radius);

    /**Amination*/
    for(double t=0; t<T; t++) {
      /**Draw the background*/
      StdDraw.clear();
      StdDraw.picture(0, 0, starfiled);

      for(int a=0; a<bodies.length; a++) {
        /**Update the bodies*/
        double f_x = bodies[a].calcNetForceExertedByX(bodies);
        double f_y = bodies[a].calcNetForceExertedByY(bodies);
        bodies[a].update(dt, f_x, f_y);
        /**Draw the bodies*/
        bodies[a].draw();
      }

      StdDraw.show();
    }

    /**Print the universe at the end*/
    StdOut.printf("%d\n", bodies.length);
    StdOut.printf("%.2e\n", radius);
    for (int i = 0; i < bodies.length; i++) {
        StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                      bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                      bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);
    }

  }
}
