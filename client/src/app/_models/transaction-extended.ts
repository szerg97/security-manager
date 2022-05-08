import { Gsec } from "./gsec";

export interface TransactionExtended extends Gsec{
    denomination: number;
    netValue: number;
    yield: number;
    referenceYield: number;

    customerName: string;
    customerEmail: string;
    customerPhone: string;
    registered: Date;

    securityName: string;
    issuerName: string;
}