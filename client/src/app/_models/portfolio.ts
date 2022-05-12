import { Gsec } from "./gsec";
import { Transaction } from "./transaction";

export interface Portfolio extends Gsec{
    balance: number;
    securitiesTotal: number;
    denominationTotal: number;
    total: number;
    customerName: string;
    customerEmail: string;
    transactions?: Transaction[];
}