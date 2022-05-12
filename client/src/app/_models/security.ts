import { Gsec } from "./gsec";

export interface Security extends Gsec{
  name: string;
  description: string;
  currency: string;
  exchangeRate?: number;
  faceValue: number;
  accruedInterest?: number;
  interest: number;
  fixedInterest: boolean;
  term?: number;
  expiration?: Date;
  frequency?: number;
}
