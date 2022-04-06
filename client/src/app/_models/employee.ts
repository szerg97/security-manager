import { Gsec } from "./gsec";

export interface Employee extends Gsec{
    firstName: string;
    lastName: string;
    email: string;
    phone: string;
}