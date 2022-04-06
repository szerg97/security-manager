import { Gsec } from "./gsec";

export interface Portfolio extends Gsec{
    balance: string;
    customerId: string;
}