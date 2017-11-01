// this lib is supposed to add Arduino SPI slave sender function ;)

#include<SPI.h>
#include "Send.h"
#include "config.h"

int value[7];
int cal[7];
volatile byte Svalreceived,Svalsent;
volatile int printed[7] = {1,1,1,1,1,1,1};
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

ISR (SPI_STC_vect)
{
  Svalreceived = SPDR;
  
  SPDR = printed[cnt++];
  cnt %= 6;
  
  //active = true;
}

void sendSU(){ // sends data both on SPI and UART
        
  for(int i=0; i<4; i++){
    if(i==0 || i==1) {
      printed[i] = abs(value[i]-cal[i]-OFFSET + (value[4]-cal[4])); // ~edit
    }

    if(i==2 || i==3) {
      printed[i] = abs(value[i]-cal[i]+OFFSET + (value[4]-cal[4])); // ~edit
    }
    Serial.print((char)printed[i]);
//    Serial.print(" ");
  }
  for(int i=4; i<6; i++){
    printed[i] = value[i]-cal[i]+127;
    Serial.print((char)printed[i]);
//    Serial.print(" ");
  }
  printed[6] = (int)(value[6]-cal[6])+OFFSETZ;
  Serial.print((char)printed[6]);
//  Serial.print((char)0); //sync byte null for model3d
  delay(10);
    
}
