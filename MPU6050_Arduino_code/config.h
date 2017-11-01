/* config_H_ */

#define PINOUT STANDARD // standard output mapping	
//	4	5	6	7	8
//	FL	FR	RL	RR	Centre

// if above definition is commented the mapping is as follows
//	5	4	8	7	6
//	FL	FR	RL	RR	Centre

#define MULT 6	// multiplier of readings 
				// higher multiplier = better resolution
				// lower multiplier = better precision
				// for MULT == 10: output of 10 = 1 degree
				// for MULT == 6: output of 6 = 1 degree
				// output range: 0-255
#define MULTZ 2.83333333333	// multipler of rotation values, same rules apply
                            // 2.83333333333 = 255/180 * 2
                            // we need to make 180 divisible by MULTZ * 255
                            // for MULTZ == 0.5 we have full rotation, low precision
#define OFFSET 140	// middle point for output values 0-255
#define OFFSETZ 127	// middle point for Z axis
                    // 90 for full resolution (when 360/2==180 < 255) (MULTZ == 0.5)
                    // 127 = 255/2 when middle point for less than full rotation

#define DELAY 10 	// delay of sending loop in ms
					// can be set to 0
					// exceeding 60 loops/s corrupts 3d animation
					// when all 5 gyros are on the car, the delay isn't needed
