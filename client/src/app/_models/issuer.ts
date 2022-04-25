import { Gsec } from "./gsec";

export interface Issuer extends Gsec{
    name: string;
    email: string;
    phone: string;
    status: boolean;
    address: string;
    city: string;
    country: string;
    weekdays: string;
    saturday: string;
    sunday: string;
}