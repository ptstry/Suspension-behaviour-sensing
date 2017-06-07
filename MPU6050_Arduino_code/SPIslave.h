// this lib is supposed to add Arduino SPI slave sender function ;)

//#define OFFSET 120
#define OFFSET 90

#include<SPI.h>

volatile byte Svalreceived,Svalsent;
volatile int value[7];
volatile int cal[7];
volatile int printed[7];
volatile uint8_t cnt=1;
bool active=true;
int tmer=0;
unsigned long time=0;


void SPIsetup(){
  //  Set MISO pin as  output
  pinMode(MISO, OUTPUT);
  //  turn    on SPI in  slave   mode    
  SPCR |= _BV(SPE);
  //  get ready   for an  interrupt       
  // process_it = false;
  //  now turn on  interrupts  
  SPI.attachInterrupt();
  SPDR = 0;
}

//ISR (SPI_STC_vect)
//{
//  Svalreceived = SPDR;
//  
//  SPDR = value[cnt++];
//  cnt %= 4;
//  
//  //active = true;
//}

ISR (SPI_STC_vect)
{
  Svalreceived = SPDR;
  
  SPDR = printed[cnt++];
  cnt %= 4;
  
  //active = true;
}

void SPIsend(byte value) {
  if(active){
    SPDR = value;
    active = false;
  }
}

// eg. use: SPIsend2(value); // where value is declared as: byte value[4];
void SPIsend2(byte *value){ // 4 different readongs one ofter another
  if(active){
    SPDR = value[cnt];
    cnt%=2;
    active=false;
  }
}

//// eg. use: SPIsend4(value); // where value is declared as: byte value[4];
//void SPIsend4(byte *value){ // 4 different readongs one ofter another
//  if(active){
//    SPDR = value[cnt];
//    cnt%=4;
//    active=false;
//  }
//}

void SPIsend4(){ // 4 different readongs one ofter another
  //byte printed;
  for(int i=0; i<4; i++){
    //while(!active) { }
    //SPDR = value[i];
//    printed[i] = abs(value[i]-cal[i]+OFFSET - (value[4]-cal[4])); // ~edit
    if(i==0 || i==1) {
      printed[i] = abs(value[i]-cal[i]+OFFSET - (value[4]-cal[4])); // ~edit
    }

    if(i==2 || i==3) {
      printed[i] = abs(value[i]-cal[i]-OFFSET - (value[4]-cal[4])); // ~edit
      //printed[i] *= -1;
    }
    Serial.print((char)printed[i]);
    //Serial.print(cal[i]);
//    Serial.print(" ");
//    if(i==3) Serial.println("");
  }
  for(int i=4; i<7; i++){
    printed[i] = value[i]-cal[i]+127;
    Serial.print((char)printed[i]);
  }
  Serial.print((char)0);
    tmer++;
    if(tmer==99){
      //unsigned long x = millis()-time;
      //Serial.println(x/100);
      //time=millis();
      //tmer=0;
    }
    //Serial.print(" ");
    
}
