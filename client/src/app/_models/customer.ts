import { Gsec } from "./gsec";
import { Portfolio } from "./portfolio";

export interface Customer extends Gsec{
    firstName: string;
    lastName: string;
    email: string;
    phone: string;
    idCard: string;
    gender: boolean;
    dateOfBirth: Date;
    registered: Date;
    address: string;
    city: string;
    country: string;
    portfolio: Portfolio;
}