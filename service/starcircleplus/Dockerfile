FROM amazoncorretto:17
RUN echo $' \
public class Hello { \
public static void main(String[] args) { \
System.out.println("Welcome to Amazon Corretto!"); \
} \
}' > Hello.java
RUN javac Hello.java
CMD ["java", "Hello"] 
WORKDIR /service/starcircleplus
EXPOSE 8080
CMD []
