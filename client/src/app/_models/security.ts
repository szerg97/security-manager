import { Gsec } from "./gsec";

export interface Security extends Gsec{
  name: string;
  description: string;
  currency: string;
  exchangeRate: string;
  faceValue: string;
  interest: string;
  fixedInterest: boolean;
}
