(ns day-5)

(def input [2 1 1 2 0 -4 0 -4 0 0 2 -9 -6 -2 -11 -5 -6 -9 -6 -17 -9 -19 -9 -10 -13 -12 -21 -21 2 -12 -22 -20 -12 -21 -13 -28 -9 -20 -26 -21 -28 -3 -21 -11 -10 -32 -15 -42 0 -31 -26 -45 2 -49 -48 -38 -52 -28 -8 -29 -30 -1 -17 -58 -1 -16 -27 -21 -34 0 -7 -45 -13 -5 -28 -11 -74 -75 -10 -50 -20 -31 -19 -78 -80 -42 -24 -50 -65 -15 -29 -34 -3 -67 -36 -69 -95 -59 -69 -3 -92 -92 0 -78 1 -22 -89 -25 -42 -99 -65 0 -51 -67 -38 -103 -8 -69 -64 -54 -27 -29 -47 0 -123 -53 -25 -62 2 -38 -18 0 -94 -85 -19 -19 -76 -25 -49 -111 -56 -103 -79 -124 -107 -38 -14 -69 -32 -1 -113 -109 -20 -73 -15 -101 -132 0 -72 -28 -91 -89 0 -130 -32 -148 -28 -166 -28 -15 -57 -143 -16 -88 -158 -172 -126 -21 -148 1 -10 -2 -24 -174 -108 -2 -150 -79 -1 -72 -108 -126 -158 -153 -125 -47 -107 -79 -168 -93 -160 -140 -200 -131 -188 -103 -20 -122 -153 -170 -122 -144 -153 -176 -168 -76 -163 2 -112 -126 -7 -74 -128 -144 -20 -220 -102 -177 -3 -172 -26 -3 -78 -179 -120 -20 -16 -175 -78 -84 -153 -154 -23 -29 -161 -222 -202 -31 -169 -244 -242 -56 -32 -62 -81 -6 -178 -25 -121 -122 -99 -110 -86 -23 -255 -6 -235 -63 -1 -80 -161 -71 -93 -75 -16 -139 -101 -94 -136 -21 -254 -214 -258 -56 -101 -103 -124 -250 -58 -136 -62 -192 -232 -261 0 -21 -255 -51 -175 -222 -63 -290 -134 -19 -243 -272 -112 -279 -110 -261 -192 -44 -90 -307 -106 -118 -4 -27 -175 -178 -309 -11 -86 -124 -250 -299 -284 -264 -11 -136 -29 -174 -3 -163 -211 -226 -271 -63 -283 -98 -335 -299 2 -104 -219 0 -329 -33 -248 -268 -121 -294 -41 -206 -3 -115 -256 -326 -168 -62 -221 -51 -113 -113 -286 -105 -126 -8 -88 -1 -344 -266 -201 -175 -52 -109 -192 -272 -197 -45 -218 -181 -329 -355 -330 1 -57 -226 -200 -213 -387 -148 -28 -65 -283 -145 -37 -299 -189 -387 -46 -290 -358 -51 -89 -52 0 -279 -342 -243 -257 -244 -83 -152 -218 -194 -79 -130 -162 -394 -280 -140 -280 -95 -22 -424 -204 -34 -127 -384 -161 -248 -7 -99 -381 -173 -147 -171 -9 -377 -319 -248 -61 -263 -343 -434 -257 -297 -419 -413 -387 -448 -221 -248 -178 -32 -50 -327 -127 -388 -13 -349 -312 -225 -200 -374 -252 -457 -165 -244 -61 -125 -26 -127 -59 -334 -391 -20 -63 -66 -111 -219 -363 -354 -430 -29 -485 -427 -481 -466 -63 -336 -85 -28 -305 -346 -207 -202 -291 -373 -128 -421 -64 -315 -160 -54 -282 -63 -116 -209 -332 -27 -140 -492 -343 -18 -84 -291 -305 -429 -442 -194 -447 -126 -381 -328 -214 -179 -93 2 -408 -278 -381 -105 -527 -213 -77 -320 -191 -460 -484 -184 -42 -508 -316 -350 -86 -310 -226 -347 -365 -58 -49 -167 -329 -281 -68 -23 -66 -532 -549 -126 -510 -419 -305 -47 -291 -99 -96 -318 -174 -199 -317 -520 -194 -422 -247 -539 -21 -340 -545 -145 -159 -145 -25 -515 -559 -500 -289 -75 -351 -417 -564 -74 -176 -476 -585 -513 -590 -235 -342 -403 -161 -196 -197 -528 -343 -385 -557 -99 -514 -135 -159 -101 -559 -363 -155 -186 -127 -532 -195 -383 -554 -92 -160 -142 -93 -349 -180 -393 -68 -575 -553 -496 -173 -3 -267 -382 -394 -44 -65 -316 -25 -390 -110 -418 -315 -220 -453 -428 -77 -298 -44 -412 -493 -588 -90 -418 -296 -355 -89 -176 -628 -623 -352 -401 -614 -318 -353 -298 -256 -327 -560 -347 -321 -309 -232 -89 -22 -522 -380 -553 -568 -570 -174 -278 -422 -561 -223 -375 -555 -184 -218 -54 -135 -582 -490 -122 -447 -384 -285 -131 -246 -222 -431 -41 -340 -417 0 -186 -346 -682 -670 -616 -245 -453 -282 -565 -442 -135 -224 -516 -534 -208 -173 -413 -295 -180 -595 -524 -207 -500 -102 -704 -705 -519 -118 -523 -174 -596 -115 -596 -124 -530 -78 -526 -72 -730 -330 -722 -116 -273 -19 -293 -745 -269 -591 -660 -445 -79 -267 -182 -428 -347 -448 -137 -171 -350 -48 -385 -641 -573 -690 -729 -310 -25 -555 -101 -112 -578 -102 -778 -374 -292 -270 -128 -159 -219 -703 -250 -554 -778 -311 -417 -645 -38 -397 -21 -117 -571 -27 -267 -443 -93 -166 -395 -631 -582 -434 -21 -322 -537 -624 -472 -631 -34 -810 -251 -558 -779 -445 -519 -486 -245 -332 -758 -507 -264 -706 -744 -334 -714 -266 -478 -726 -747 -782 -386 -508 -471 -154 -111 -401 -595 -790 -501 -266 -66 -257 -20 -432 -22 -409 -228 -671 -767 -655 -368 -640 -746 -714 -323 -678 -137 -613 -309 -566 -755 -164 -461 -825 -569 -282 -775 -564 -215 -576 -770 -103 -553 -335 -153 -616 -707 -243 -336 -632 -399 -491 -612 -864 -609 -6 -215 -860 -570 -605 -88 -81 -161 -818 -806 -793 -270 -808 -754 -302 -486 -433 -734 -576 -762 -72 -859 -719 -645 -172 -853 -756 -234 -58 -177 -159 -756 -205 -894 -904 -614 -117 -642 -782 -200 -853 -2 -335 -36 -759 -102 -716 -902 -917 -101 -902 -810 -384 -883 -366 -20 -328 -409 -97 -806 -560 -386 -324 -581 -582 -203 -43 -757 -289 -867 -835 -449 -72 -677 -261 -249 -658 -502 -130 -575 -213 -433 -77 -866 -749 -267 -220 -239 -705 -825 -494 -980 -902 -152 -843 -921 -332 -880 -136 -310 -245 -482 -521 -773 -987 -220 -96 -924 -997 -645 -944 -546 -665 -254 -664 -631 -587 -91 -115 -597 -422 -65 -772 -557 -113 -899 -676 -17 -540 -160 -824 -67 -469 -493 -347 -535 -768 -618 -790 -943 -352 -601 -869 -623 -975 -368 -842 -794 -827 -1034 -438 -221 -823 -655 -128 -657 -22 -200 -803 -307 -901 -2 -861 -988 -112 -215 0 -210 -261 -1014 -147 -485 -84 -753 -674 -701 -151 -883 -467 -990 -777 -1042 -509 -604 -885 -1022 -524 -736 -23 -255 -581 -837 -342 -852 -58 -666 -455 -498 -92 -1030])

(defn run-part-1 [array]
      (loop [position 0
             counter 0
             input array]
            (let [val (get input position)]
                 (if (not (nil? val))
                   (recur (+ val position) (inc counter) (assoc input position (inc val)))
                   counter))))

(defn run-part-2 [array]
      (loop [position 0
             counter 0
             input array]
            (let [val (get input position)]
                 (if (not (nil? val))
                   (let [next-val (if (>= val 3) (dec val) (inc val))]
                     (recur (+ val position) (inc counter) (assoc input position next-val)))
                   counter))))