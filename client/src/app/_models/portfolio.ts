import { Gsec } from "./gsec";

export interface Portfolio extends Gsec{
    balance: number;
    securitiesTotal: number;
    denominationTotal: number;
    total: number;
    customerName: string;
    customerEmail: string;
}